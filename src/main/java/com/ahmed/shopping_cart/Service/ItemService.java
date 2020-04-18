package com.ahmed.shopping_cart.Service;

import javax.validation.constraints.NotNull;

import com.ahmed.shopping_cart.data.Item;


import com.ahmed.shopping_cart.model.ItemRequestModel;
import com.ahmed.shopping_cart.model.ItemResponseModel;
import org.springframework.validation.annotation.Validated;

@Validated
public interface ItemService {
    Iterable<Item> getAllItemss();

    Item addItem(@NotNull(message = "item cannot be null") ItemRequestModel entry);
    
    ItemResponseModel getItemById(Long id);

	String deleteItem(Long id);
	
	Item editItem(Item item);
}