package com.ahmed.shopping_cart.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.ahmed.shopping_cart.data.CartItem;

import org.springframework.validation.annotation.Validated;

@Validated
public interface CartItemService{
      CartItem create(@NotNull (message = "Cart must contain items") @Valid CartItem cartItem);
}