package com.ahmed.shopping_cart.model;

import com.ahmed.shopping_cart.data.CartItem;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

public class CartUpdateRequest {
    private List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    //Cart Total Price
    @Transient
    public Double getTotalPrice() {
        double sum = 0;
        List<CartItem> cartItems = getCartItems();
        for (CartItem item : cartItems) {
            sum += item.getTotalPrice();
        }

        return sum;
    }
}
