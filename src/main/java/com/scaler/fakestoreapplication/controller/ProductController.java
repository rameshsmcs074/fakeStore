package com.scaler.fakestoreapplication.controller;

import com.scaler.fakestoreapplication.DTO.ProductResponseDTO;
import com.scaler.fakestoreapplication.model.Product;
import com.scaler.fakestoreapplication.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController //Tells the JVM that all the APIs reside here for the Product Model
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products/{id}")
    public ProductResponseDTO getProduct(@PathVariable int id) {
        Product product = productService.getProduct(id);

        if(product == null) {
            return null;
        }

        ProductResponseDTO outputDTO = new ProductResponseDTO();
        outputDTO.setId(String.valueOf(product.getId()));
        outputDTO.setTitle(product.getName());
        outputDTO.setPrice(String.valueOf(product.getPrice()));
        outputDTO.setDescription(product.getDescription());
        outputDTO.setCategory(product.getCategory().getName());
        return  outputDTO;
    }

}
