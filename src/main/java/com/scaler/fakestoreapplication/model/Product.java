package com.scaler.fakestoreapplication.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {
    private int id;
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private Category category;
}
