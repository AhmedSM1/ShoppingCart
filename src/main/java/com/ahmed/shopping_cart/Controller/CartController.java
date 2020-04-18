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
public class CartController{

     
        @Autowired
        CartService cartService;
        @Autowired
        ItemService itemService;
        @Autowired
        CartItemService cartItemService;
        @Autowired
        CartRepo repo;
        @Autowired
        ItemRepo itemRepo;


      @GetMapping
      @ResponseStatus(HttpStatus.OK)
      public Iterable<Cart> getAllCarts(){
          return  repo.findAll();
      }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public ResponseEntity<Cart> create(@RequestBody @Valid List<CartItemDto> itemsRequested){
          Cart cart = new Cart();
          cart = this.cartService.create(cart);
          List<CartItem> cartItems = new ArrayList<>();
          for (CartItemDto dto : itemsRequested) {
              Optional<Item> item = itemRepo.findById(dto.getItem().getItemId());
              cartItems.add(cartItemService.create(
                 new CartItem(
            cart,item.get(), dto.getQuantity())
           ));
          }
           cart.setCartItems(cartItems);
           this.cartService.update(cart);
           return ResponseEntity.status(HttpStatus.CREATED).body(cart);
        }
    }

    
