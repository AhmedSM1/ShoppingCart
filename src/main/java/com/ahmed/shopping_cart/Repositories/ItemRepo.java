package com.ahmed.shopping_cart.Repositories;

import com.ahmed.shopping_cart.data.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;



public interface ItemRepo extends JpaRepository<Item, Long> {
    
    
}