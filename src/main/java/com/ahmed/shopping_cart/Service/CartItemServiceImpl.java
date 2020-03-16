package com.ahmed.shopping_cart.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.ahmed.shopping_cart.Repositories.CartItemRepo;

import com.ahmed.shopping_cart.model.CartItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {

  private CartItemRepo repo;

  @Autowired
  public CartItemServiceImpl(CartItemRepo cartItemRepo) {
    this.repo = cartItemRepo;
  }

  @Override
  public CartItem create(@NotNull(message = "Cart must contain items") @Valid CartItem cartItem) {
   
    return this.repo.save(cartItem);
  }


    


   

}