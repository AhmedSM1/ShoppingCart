package com.ahmed.shopping_cart.Repositories;

import com.ahmed.shopping_cart.model.Item;

import org.springframework.data.repository.CrudRepository;



public interface ItemRepo extends CrudRepository<Item, Long> {
    
    
}