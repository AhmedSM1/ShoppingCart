package com.ahmed.shopping_cart.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.persistence.Table;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "cart")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="cartItems")
public class Cart{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy") 
    private LocalDate dateCreated;

    

 
    @OneToMany(mappedBy = "primaryKey.cart")
    @Valid
    private List<CartItem> cartItems = new ArrayList<>();

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

    @Transient
    public int numberOfItems(){
        return this.cartItems.size();
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