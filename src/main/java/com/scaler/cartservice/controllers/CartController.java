package com.scaler.cartservice.controllers;

import com.scaler.cartservice.models.Cart;
import com.scaler.cartservice.services.CartService;
import com.scaler.cartservice.services.FakeCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class CartController {
    private CartService cartService;

    CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/carts")
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/carts/{id}")
    public Cart getSingleCart(@PathVariable("id") Long id) {
        return cartService.getSingleCart(id);
    }

    @GetMapping("/carts/user/{id}")
    public List<Cart> getUserCarts(@PathVariable("id") String id) {
        return cartService.getUserCarts(id);
    }

    @GetMapping("/carts")
    public List<Cart> getInDateRange(@RequestParam String startdate, @RequestParam String enddate) {
        return cartService.getInDateRange(startdate, enddate);
    }

    @PostMapping("/carts")
    public Cart addCart(@RequestBody Cart cart) {
        return cartService.addCart(cart);
    }

    @DeleteMapping("/carts/{id}")
    public void deleteCart(@PathVariable("id") Long id) {
        cartService.deleteCart(id);
    }

    @PatchMapping("/carts/{id}")
    public Cart updateCart(@PathVariable("id") Long id) {
        return cartService.updateCart(id);
    }
}
