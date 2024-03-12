package com.scaler.cartservice.services;

import com.scaler.cartservice.models.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getAllCarts();
    Cart getSingleCart(Long id);
    List<Cart> getUserCarts(String userId);
    List<Cart> getInDateRange(String urlParameter, String queryParameter);
    Cart addCart(Cart newCart);
    void deleteCart(Long id);
    Cart updateCart(Long id);
}
