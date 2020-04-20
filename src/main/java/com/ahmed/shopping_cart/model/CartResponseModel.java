package com.ahmed.shopping_cart.model;

import com.ahmed.shopping_cart.data.CartItem;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Transient;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CartResponseModel {

    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;

    private List<CartItem> cartItems = new ArrayList<>();
    private double totalPrice;

    public CartResponseModel(Long id, LocalDate dateCreated, List<CartItem> cartItems, double totalPrice) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
