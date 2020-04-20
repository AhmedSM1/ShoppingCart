package com.ahmed.shopping_cart.Service;




import com.ahmed.shopping_cart.Repositories.ItemRepo;
import com.ahmed.shopping_cart.data.Cart;
import com.ahmed.shopping_cart.data.Item;

import com.ahmed.shopping_cart.model.CartResponseModel;
import com.ahmed.shopping_cart.model.ItemRequestModel;
import com.ahmed.shopping_cart.model.ItemResponseModel;
import com.ahmed.shopping_cart.model.ItemUpdateModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemRepo itemRepo;

    @Autowired
    public ItemServiceImpl(final ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }


    @Override
    public List<ItemResponseModel> getAllItems() {
            List<Item> itemList = itemRepo.nativeGetAllItems();
            return itemList.stream().map(b->new ItemResponseModel(
                    b.getItemTitle(),
                    b.getItemPrice(),
                    b.getItemDescription(),
                    b.getPictureUrl()))
                    .collect(Collectors.toList());
    }

    @Override
    public Item addItem(@NotNull(message = "item cannot be null") ItemRequestModel entry) {
        Item item = new Item();
        item.setItemTitle(entry.getItemTitle());
        item.setItemPrice(entry.getItemPrice());
        item.setItemDescription(entry.getItemDescription());
        item.setPictureUrl(entry.getPictureUrl());
        itemRepo.nativeInset(item.getItemTitle(),item.getItemPrice(),item.getItemDescription(),item.getPictureUrl());
        return item;
    }


    @Override
    public ItemResponseModel getItemById(Long id) {
        Item item = itemRepo.nativeGetItemByID(id);
        if (item == null) throw new ResourceNotFoundException();

        // Item item = itemRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ItemResponseModel dto = modelMapper.map(item,ItemResponseModel.class);
        return dto;
    }

    @Override
    public String deleteItem( Long id) {
      Item item = itemRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException());
      itemRepo.nativeDeleteItem(id);
      return item.getItemTitle()+" have been deleted";
    }

    @Override
    public ItemResponseModel editItem(long id, ItemUpdateModel updatedItem) {
        Item item = itemRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
        item.setItemTitle(updatedItem.getItemTitle());
        item.setItemDescription(updatedItem.getItemDescription());
        item.setItemPrice(updatedItem.getItemPrice());
        this.itemRepo.save(item);
        return new ItemResponseModel(item.getItemTitle(),item.getItemPrice(),item.getItemDescription(),item.getPictureUrl());
    }


  

}