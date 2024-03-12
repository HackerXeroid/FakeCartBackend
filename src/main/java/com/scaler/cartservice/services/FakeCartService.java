package com.scaler.cartservice.services;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.scaler.cartservice.dtos.FakeCartDto;
import com.scaler.cartservice.dtos.ProductDto;
import com.scaler.cartservice.models.Cart;
import com.scaler.cartservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class FakeCartService implements CartService {
    RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Cart> getAllCarts() {
        FakeCartDto[] fakeCartDtos = restTemplate.getForObject("https://fakestoreapi.com/carts/", FakeCartDto[].class);

        List<Cart> allCarts = new ArrayList<>();
        for (FakeCartDto fakeCartDto: fakeCartDtos) {
            allCarts.add(convertToCart(fakeCartDto));
        }
        return allCarts;
    }

    @Override
    public Cart getSingleCart(Long id) {
        FakeCartDto fakeCartDto = restTemplate.getForObject("https://fakestoreapi.com/carts/" + id, FakeCartDto.class);

        return convertToCart(fakeCartDto);
    }

    @Override
    public List<Cart> getUserCarts(String userId) {
        FakeCartDto[] fakeCartDtos = restTemplate.getForObject("https://fakestoreapi.com/carts/user/" + userId, FakeCartDto[].class);
        List<FakeCartDto> fakeCartDtosList = Arrays.asList(fakeCartDtos);
        List<Cart> userCarts = new ArrayList<>();
        for (FakeCartDto fakeCartDto: fakeCartDtosList) {
            userCarts.add(convertToCart(fakeCartDto));
        }
        return userCarts;
    }

    @Override
    public List<Cart> getInDateRange(String startdate, String enddate) {
        FakeCartDto[] fakeCartDtos = restTemplate.getForObject("https://fakestoreapi.com/carts/", FakeCartDto[].class);
        Date startDate = strToDate(startdate);
        Date endDate = strToDate(enddate);

        List<Cart> requiredCarts = new ArrayList<>();
        for (FakeCartDto fakeCartDto: fakeCartDtos) {
            String objDateString = fakeCartDto.getDate();
            Date objDate = strToDate(objDateString);
            if ((objDate.compareTo(startDate) >= 0) && (objDate.compareTo(endDate) <= 0))
                requiredCarts.add(convertToCart(fakeCartDto));
        }
        return requiredCarts;
    }

    @Override
    public Cart addCart(Cart newCart) {
            return new Cart();
//        return restTemplate.postForObject("https://fakestoreapi.com/carts/", newCart, FakeCartDto.class);
    }

    @Override
    public void deleteCart(Long id) {
        restTemplate.delete("https://fakestoreapi.com/carts/" + id, FakeCartDto.class);
    }

    @Override
    public Cart updateCart(Long id) {
        return null;
    }

    public Cart convertToCart(FakeCartDto fakeCartDto) {
        if (fakeCartDto == null) return null;
        Cart cart = new Cart();
        cart.setId(fakeCartDto.getId());
        cart.setUserId(fakeCartDto.getUserId());
        cart.setDate(new Date());
        Date date = strToDate(fakeCartDto.getDate());
        cart.setDate(date);
        cart.setProducts(fakeCartDto.getProducts());
        return cart;
    }

    public Date strToDate(String dateString) {
        Date date = Date.from(Instant.parse(dateString));
        return date;
    }
//    public Product convertToProduct(ProductDto productDto) {
//        Product product = new Product();
//        product.setId(productDto.getProductId());
//        product.setQuantity(productDto.getQuantity());
//        return product;
//    }
}

/*
* DateFormat df1 = ;
String string1 = "2001-07-04T12:08:56.235-0700";
Date result1 = df1.parse(string1);
* */