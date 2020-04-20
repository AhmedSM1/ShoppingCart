package com.ahmed.shopping_cart.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.ahmed.shopping_cart.Repositories.CartRepo;
import com.ahmed.shopping_cart.Repositories.ItemRepo;
import com.ahmed.shopping_cart.data.Cart;

import com.ahmed.shopping_cart.data.CartItem;
import com.ahmed.shopping_cart.data.CartItemDto;
import com.ahmed.shopping_cart.data.Item;
import com.ahmed.shopping_cart.model.CartRequestModel;
import com.ahmed.shopping_cart.model.CartResponseModel;
import com.ahmed.shopping_cart.model.CartUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartServiceImpl implements CartService {

  @Autowired
  private CartRepo repo;
  @Autowired
  private CartItemService cartItemService;
  @Autowired
  private ItemRepo itemRepo;

  @Override
  public CartResponseModel create(@NotNull(message = "Cart must contain items") @Valid List<CartItemDto> list) {
      //req  contains List<DTO>
      Cart cart = new Cart();
      this.repo.save(cart);
      List<CartItem> cartItemsList = new ArrayList<>();

      //map CartItemDTO to CartITem
      for (CartItemDto dto : list) {
          Optional<Item> item = itemRepo.findById(dto.getItem().getItemId());
          CartItem cartItem = new CartItem(cart,item.get(),dto.getQuantity());
          cartItemsList.add(cartItemService.create(cartItem));
      }
          cart.setCartItems(cartItemsList);
          cart.setDateCreated(LocalDate.now());
          cart.getTotalPrice();
          this.repo.save(cart);
          return new CartResponseModel(cart.getId(), cart.getDateCreated(), cart.getCartItems(), cart.getTotalPrice());
      }



    @Override
    public void update(@NotNull(message = "Cart must contain items") @Valid CartUpdateRequest updateRequest, Long id) {
        Cart cart = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException());
        List<CartItem> cartItemsList = cart.getCartItems();
        cart.getTotalPrice();
        this.repo.save(cart);
    }



  @Override
  public List<CartResponseModel> getAllCarts() {
      List<Cart> cartList = repo.findAll();
      return cartList.stream().map(b->new CartResponseModel(
              b.getId(),
              b.getDateCreated(),
              b.getCartItems(),
              b.getTotalPrice()))
              .collect(Collectors.toList());

  }



   
   
   


}