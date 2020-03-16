package com.ahmed.shopping_cart.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;





//Many to one  with item table and cart table
@Entity

public class CartItem {

 

    // Cart id and item it
     @EmbeddedId
     @JsonIgnore
     private cartItemPK primaryKey;

     @Column(nullable = false) 
     private Integer quantity;

     public CartItem(Cart cart , Item item , Integer quantity) {
        this.primaryKey = new cartItemPK();
        primaryKey.setCart(cart);
        primaryKey.setItem(item);
        this.quantity = quantity;
        
    }

    

    public CartItem() {
        super();
    }


     @JsonIgnore
     public cartItemPK getPk() {
         return primaryKey;
     }

     public void setPk(cartItemPK pk) {
         this.primaryKey = pk;
     }

     public Integer getQuantity() {
         return quantity;
     }

     public void setQuantity(Integer quantity) {
         this.quantity = quantity;
     }


     @Transient
     public Item getItem(){
         return this.primaryKey.getItem();
     }

     @Transient
     public Double getTotalPrice(){
         return getItem().getItemPrice() * getQuantity();
     }

     @Override
     public int hashCode() {
         final int prime = 31;
         int result = 1;
         result = prime * result + ((primaryKey == null) ? 0 : primaryKey.hashCode());
         result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
         return result;
     }

     @Override
     public boolean equals(Object obj) {
         if (this == obj)
             return true;
         if (obj == null)
             return false;
         if (getClass() != obj.getClass())
             return false;
         CartItem other = (CartItem) obj;
         if (primaryKey == null) {
             if (other.primaryKey != null)
                 return false;
         } else if (!primaryKey.equals(other.primaryKey))
             return false;
         if (quantity == null) {
             if (other.quantity != null)
                 return false;
         } else if (!quantity.equals(other.quantity))
             return false;
         return true;
     }


}