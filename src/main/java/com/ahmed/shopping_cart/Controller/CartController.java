package com.ahmed.shopping_cart.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.ahmed.shopping_cart.Repositories.CartRepo;
import com.ahmed.shopping_cart.Service.CartItemService;
import com.ahmed.shopping_cart.Service.CartService;
import com.ahmed.shopping_cart.Service.ItemService;
import com.ahmed.shopping_cart.model.Cart;
import com.ahmed.shopping_cart.model.CartItem;
import com.ahmed.shopping_cart.model.CartItemDto;

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
      

      //   @GetMapping
      //   @ResponseStatus(HttpStatus.OK)
      //   public @NotNull Iterable<Cart> getCarts(){
      //         return this.cartService.getAllCarts();
      //   }


      @GetMapping
      public Iterable<Cart> getAllCarts(){

          return  repo.findAll();
      }

        @PostMapping
        public ResponseEntity<Cart> create(@RequestBody @Valid List<CartItemDto> itemsRequested){
          
         
          Cart cart = new Cart();
         
       
          cart = this.cartService.create(cart);

          
          

 
          List<CartItem> cartItems = new ArrayList<>();


          for (CartItemDto dto : itemsRequested) {

           cartItems.add(cartItemService.create(
                 new CartItem(
            cart, 
           itemService.getItemById(dto.getItem().getItemId()), dto.getQuantity())
           
           )); }
       
           cart.setCartItems(cartItems);

           this.cartService.update(cart);

           return ResponseEntity.status(HttpStatus.CREATED).body(cart);
        }


       

    }

    
