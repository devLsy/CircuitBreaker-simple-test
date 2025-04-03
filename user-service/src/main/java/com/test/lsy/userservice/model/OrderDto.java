package com.test.lsy.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
    private int id;
    private String name;
    private String category;
    private String color;
    private double price;
}


