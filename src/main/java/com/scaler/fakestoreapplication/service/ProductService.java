package com.scaler.fakestoreapplication.service;

import com.scaler.fakestoreapplication.model.Product;
import java.util.List;

public interface ProductService {
    public Product getProduct(int id);
    List<Product> getProducts();
}
