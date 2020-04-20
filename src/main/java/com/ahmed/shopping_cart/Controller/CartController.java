package com.ahmed.shopping_cart.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ahmed.shopping_cart.Repositories.CartRepo;
import com.ahmed.shopping_cart.Repositories.ItemRepo;
import com.ahmed.shopping_cart.Service.CartItemService;
import com.ahmed.shopping_cart.Service.CartService;
import com.ahmed.shopping_cart.Service.ItemService;
import com.ahmed.shopping_cart.data.Cart;
import com.ahmed.shopping_cart.data.CartItem;
import com.ahmed.shopping_cart.data.CartItemDto;

import com.ahmed.shopping_cart.data.Item;
import com.ahmed.shopping_cart.model.CartRequestModel;
import com.ahmed.shopping_cart.model.CartResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService service;



    @GetMapping
    public ResponseEntity<List<CartResponseModel>> getAllCart(){
        return new ResponseEntity<>(service.getAllCarts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CartResponseModel> create(@RequestBody List<CartItemDto> cartItemDtoList){
        return new ResponseEntity<>(service.create(cartItemDtoList), HttpStatus.CREATED);
    }


}