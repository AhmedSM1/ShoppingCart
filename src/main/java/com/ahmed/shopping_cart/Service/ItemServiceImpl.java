package com.ahmed.shopping_cart.Service;




import com.ahmed.shopping_cart.Repositories.ItemRepo;
import com.ahmed.shopping_cart.data.Item;

import com.ahmed.shopping_cart.model.ItemRequestModel;
import com.ahmed.shopping_cart.model.ItemResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemRepo itemRepo;

    @Autowired
    public ItemServiceImpl(final ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    @Override
    public Iterable<Item> getAllItemss() {
       Iterable<Item> itemList = itemRepo.findAll();
        return itemList;
    }

    @Override
    public Item addItem(@NotNull(message = "item cannot be null") ItemRequestModel entry) {
        Item item = new Item();
        item.setItemTitle(entry.getItemTitle());
        item.setItemPrice(entry.getItemPrice());
        item.setItemDescription(entry.getItemDescription());
        item.setPictureUrl(entry.getPictureUrl());
        itemRepo.save(item);
        return item;
    }


    @Override
    public ItemResponseModel getItemById(Long id) {
        Item item = itemRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ItemResponseModel dto = modelMapper.map(item,ItemResponseModel.class);
        return dto;
    }

    @Override
    public String deleteItem( Long id) {
    //   Item item = itemRepo.findById(id);
        
    //    itemRepo.delete(item);
      return "Item have been deleted";

    }

    @Override
    public Item editItem( Item updatedItem) {
       Item item = itemRepo.findById(updatedItem.getItemId()).orElseThrow(() -> new ResourceNotFoundException());
       item.setItemTitle(updatedItem.getItemTitle());
       item.setItemDescription(updatedItem.getItemDescription());
       item.setItemPrice(updatedItem.getItemPrice());
       itemRepo.save(item);
       return item;

    }

  

}