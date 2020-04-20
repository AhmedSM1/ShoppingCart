package com.ahmed.shopping_cart.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.ahmed.shopping_cart.data.Cart;

import com.ahmed.shopping_cart.data.CartItemDto;
import com.ahmed.shopping_cart.model.CartRequestModel;
import com.ahmed.shopping_cart.model.CartResponseModel;
import com.ahmed.shopping_cart.model.CartUpdateRequest;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@Validated
public interface CartService {
    
    CartResponseModel create(@NotNull(message = "Cart must contain items") @Valid List<CartItemDto> cartItemDtoList);
    
    void update(@NotNull(message = "Cart must contain items") @Valid CartUpdateRequest cart, Long id);

    List<CartResponseModel> getAllCarts();
}