package com.ahmed.shopping_cart.Repositories;

import com.ahmed.shopping_cart.data.CartItem;
import com.ahmed.shopping_cart.data.cartItemPK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;



public interface CartItemRepo extends JpaRepository<CartItem,cartItemPK> {

}