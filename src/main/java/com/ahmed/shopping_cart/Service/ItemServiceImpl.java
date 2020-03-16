package com.ahmed.shopping_cart.Service;




import com.ahmed.shopping_cart.Repositories.ItemRepo;
import com.ahmed.shopping_cart.model.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Item addItem( Item item) {
      Item item2 =  itemRepo.save(item);
       return item2;
    }

    @Override
    public Item getItemById( Long id) {
        Item item = itemRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException());
        return item;
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