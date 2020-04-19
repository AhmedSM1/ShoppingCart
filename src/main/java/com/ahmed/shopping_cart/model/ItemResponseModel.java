package com.ahmed.shopping_cart.model;

public class ItemResponseModel {
    private String itemTitle;
    private double itemPrice;
    private String itemDescription;
    private String pictureUrl;

    public ItemResponseModel (String itemTitle, double itemPrice, String itemDescription, String pictureUrl) {
        this.itemTitle = itemTitle;
        this.itemPrice = itemPrice;
        this.itemDescription = itemDescription;
        this.pictureUrl = pictureUrl;
    }

    public ItemResponseModel() {
    }

    @Override
    public String toString() {
        return "ItemResponseModel{" +
                "itemTitle='" + itemTitle + '\'' +
                ", itemPrice=" + itemPrice +
                ", itemDescription='" + itemDescription + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                '}';
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
