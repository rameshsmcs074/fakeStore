package com.scaler.fakestoreapplication.service;

import com.scaler.fakestoreapplication.DTO.ProductResponseDTO;
import com.scaler.fakestoreapplication.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//! FakeStoreProdutService has to connect with external API prodiver - https://fakestoreapi.com/
//! Will have to use RestTemplate (This has to created in Application context, so that we need to recreate it everytime it is needed)
@Service
public class FakeStoreProductService implements  ProductService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Product getProduct(int id) {
        System.out.println("Get product by id: " + id);
        ProductResponseDTO productResponseDTO = restTemplate.getForObject("https://fakestoreapi.com/products/"+id, ProductResponseDTO.class);

        if(productResponseDTO == null)
            return null;
        System.out.println(productResponseDTO.toString());
        return productResponseDTO.toProduct();
    }
}
