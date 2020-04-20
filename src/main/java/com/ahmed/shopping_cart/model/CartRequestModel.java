package com.ahmed.shopping_cart.model;

import com.ahmed.shopping_cart.data.CartItem;
import com.ahmed.shopping_cart.data.CartItemDto;
import org.springframework.data.annotation.Transient;

import javax.persistence.OneToMany;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class CartRequestModel {

    private List<CartItemDto> cartItems = new ArrayList<>();

    public CartRequestModel(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }

    public CartRequestModel() {
    }

    public List<CartItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }


}
