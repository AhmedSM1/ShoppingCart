package com.ahmed.shopping_cart.Repositories;

import com.ahmed.shopping_cart.data.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ItemRepo extends JpaRepository<Item, Long> {
    @Query(value = "SELECT * FROM item ",nativeQuery = true)
    List<Item> nativeGetAllItems();

    @Query(value = "SELECT u  FROM Item u WHERE u.itemId = :id")
    Item nativeGetItemByID( @Param(value = "id") long id);

    @Modifying
    @Query(value = "DELETE FROM  Item u  WHERE u.itemId = :id")
    void nativeDeleteItem( @Param(value = "id") long id);


    @Modifying
    @Query(value = "INSERT INTO item(item_title,item_price,item_description,picture_url) VALUES(:itemTitle,:itemPrice,:itemDescription,:pictureUrl)" ,nativeQuery = true)
    void nativeInset(

            @Param(value = "itemTitle" )String itemTitle,
            @Param(value = "itemPrice" )double itemPrice,
            @Param(value = "itemDescription" )String itemDescription,
            @Param(value = "pictureUrl" )String pictureUrl
    );

}