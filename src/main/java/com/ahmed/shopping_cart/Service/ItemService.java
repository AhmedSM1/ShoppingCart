package com.ahmed.shopping_cart.Service;

import javax.validation.constraints.NotNull;

import com.ahmed.shopping_cart.model.Item;


import org.springframework.validation.annotation.Validated;

@Validated
public interface ItemService {
    Iterable<Item> getAllItemss();

    Item addItem(@NotNull(message = "item cannot be null") Item item);
    
    Item getItemById(Long id);

	String deleteItem(Long id);
	
	Item editItem(Item item);
}