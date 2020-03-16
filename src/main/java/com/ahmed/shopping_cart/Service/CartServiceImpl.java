package com.ahmed.shopping_cart.Service;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.ahmed.shopping_cart.Repositories.CartRepo;
import com.ahmed.shopping_cart.model.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartServiceImpl implements CartService {

  private CartRepo repo;

  @Autowired
  public CartServiceImpl(CartRepo repo) {
    this.repo = repo;
  }

  @Override
  public Cart create(@NotNull(message = "Cart must contain items") @Valid Cart cart) {
    cart.setDateCreated(LocalDate.now());

    return this.repo.save(cart);
   
  }

  @Override
  public void update(@NotNull(message = "Cart must contain items") @Valid Cart cart) {
      this.repo.save(cart);

  }

  @Override
  public Iterable<Cart> getAllCarts() {
    return this.repo.findAll();
  }



   
   
   


}