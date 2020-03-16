package com.ahmed.shopping_cart.Repositories;

import com.ahmed.shopping_cart.model.Cart;

import org.springframework.data.repository.CrudRepository;



public interface CartRepo extends CrudRepository<Cart,Long>{

 
}