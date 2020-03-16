package com.ahmed.shopping_cart.Repositories;

import com.ahmed.shopping_cart.model.CartItem;
import com.ahmed.shopping_cart.model.cartItemPK;

import org.springframework.data.repository.CrudRepository;



public interface CartItemRepo extends CrudRepository<CartItem,cartItemPK>{

}