package com.scaler.fakestoreapplication.service;

import com.scaler.fakestoreapplication.DTO.FakeStoreProductDto;
import com.scaler.fakestoreapplication.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//! FakeStoreProdutService has to connect with external API prodiver - https://fakestoreapi.com/
//! Will have to use RestTemplate (This has to created in Application context, so that we need to recreate it everytime it is needed)
@Service
public class FakeStoreProductService implements  ProductService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Product getProduct(int id) {
        System.out.println("Get product by id: " + id);
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDto.class);

        if(fakeStoreProductDto == null)
            return null;
        System.out.println(fakeStoreProductDto.toString());
        return fakeStoreProductDto.toProduct();
    }

    @Override
    public List<Product> getProducts() {
        System.out.println("Get all products");
        //! RestTemplate - has technical limitation with the fetching list from the server (web URI),
        //! Since, it does not decide on the type of the object within the List, because of Java Genrics (Erasure)
        //! Work around is to use the ResponseEntity with Array

        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class);

        FakeStoreProductDto[] fakeStoreProductDtos = response.getBody();

        List<Product> products = new ArrayList<Product>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            Product product = fakeStoreProductDto.toProduct();
            products.add(product);
        }

        return products;
    }

    @Override
    public Product addProduct(FakeStoreProductDto product) {
        FakeStoreProductDto productDto = restTemplate.postForObject("https://fakestoreapi.com/products", product, FakeStoreProductDto.class);
        return productDto.toProduct();
    }

    @Override
    public void deleteProduct(int id) {
        restTemplate.delete("https://fakestoreapi.com/products/"+id);
    }

    @Override
    public Product updateProduct(int id, FakeStoreProductDto product) {
        HttpEntity<FakeStoreProductDto> httpEntity = new HttpEntity<>(product);
        ResponseEntity<FakeStoreProductDto> response= restTemplate.exchange("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, httpEntity, FakeStoreProductDto.class);
        return response.getBody().toProduct();
    }
}
