package com.scaler.fakestoreapplication.service;

import com.scaler.fakestoreapplication.DTO.FakeStoreProductDto;
import com.scaler.fakestoreapplication.model.Product;
import java.util.List;

public interface ProductService {

    public Product getProduct(int id);

    List<Product> getProducts();

    Product addProduct(FakeStoreProductDto product);

    void deleteProduct(int id);

    Product updateProduct(int id, FakeStoreProductDto product);
}
