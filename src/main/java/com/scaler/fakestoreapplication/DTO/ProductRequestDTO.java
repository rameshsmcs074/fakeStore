package com.scaler.fakestoreapplication.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {
    private String id;
    private String title;
    private String price;
    private String category;
    private String description;
    private String image;
}
