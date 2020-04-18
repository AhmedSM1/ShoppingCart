package com.ahmed.shopping_cart.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.ahmed.shopping_cart.data.Cart;

import org.springframework.validation.annotation.Validated;


@Validated
public interface CartService {
    
    Cart create(@NotNull(message = "Cart must contain items") @Valid Cart cart);
    
    void update(@NotNull(message = "Cart must contain items") @Valid Cart cart);

    Iterable<Cart> getAllCarts();
}