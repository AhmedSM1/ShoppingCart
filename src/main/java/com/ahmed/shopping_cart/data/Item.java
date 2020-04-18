package com.ahmed.shopping_cart.data;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;



@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

	@NotEmpty(message = "Name is mandatory")
    private String itemTitle;

	@Min(value=0 ,message = "price cannot be in negative")
    private double itemPrice;

    @NotEmpty(message = "Description is mandatory")
    private String itemDescription;

    private String pictureUrl;


    public Item(Long itemId, @NotEmpty(message = "Name is mandatory") String itemTitle,
            @Min(value = 0, message = "price cannot be in negative") double itemPrice,
            @NotEmpty(message = "Description is mandatory") String itemDescription, String pictureUrl) {
        this.itemId = itemId;
        this.itemTitle = itemTitle;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.pictureUrl = pictureUrl;
    }

    public Item() {
    }


    
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

  

    



   

}
