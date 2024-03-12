package com.scaler.cartservice.dtos;

import com.scaler.cartservice.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class FakeCartDto {
    private Long id;
    private Long userId;
    private String date;
    private List<Product> products;

}
