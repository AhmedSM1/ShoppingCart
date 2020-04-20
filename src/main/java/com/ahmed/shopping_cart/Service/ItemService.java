package com.ahmed.shopping_cart.Service;

import javax.validation.constraints.NotNull;

import com.ahmed.shopping_cart.data.Item;


import com.ahmed.shopping_cart.model.ItemRequestModel;
import com.ahmed.shopping_cart.model.ItemResponseModel;
import com.ahmed.shopping_cart.model.ItemUpdateModel;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface ItemService {
    List<ItemResponseModel> getAllItems();

    Item addItem(@NotNull(message = "item cannot be null") ItemRequestModel entry);
    
    ItemResponseModel getItemById(Long id);

	String deleteItem(Long id);
	
	ItemResponseModel editItem(long id , ItemUpdateModel updateModel);
}