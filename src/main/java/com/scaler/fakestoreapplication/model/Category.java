package com.scaler.fakestoreapplication.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Category {
    private int id;
    private String name;

    public Category(String productCategory) {
        this.name = productCategory;
    }
}
