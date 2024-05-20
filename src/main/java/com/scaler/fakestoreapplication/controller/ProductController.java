package com.scaler.fakestoreapplication.controller;

import com.scaler.fakestoreapplication.DTO.FakeStoreProductDto;
import com.scaler.fakestoreapplication.model.Product;
import com.scaler.fakestoreapplication.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController //Tells the JVM that all the APIs reside here for the Product Model
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable int id) {
        Product product = productService.getProduct(id);
        return product;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(@RequestParam(required = false) Integer limit, @RequestParam(required = false) String sortingOrder) {

        List<Product> products = productService.getProducts();

        //! limiting the number of elements based on the request param
        if(limit!=null)
        {
            products = products.subList(0, limit);
        }
        //! sort the elements if the value is not ASC
       if("DEC".equals(sortingOrder)) {
           Collections.sort(products, Collections.reverseOrder(new ProductsComparatorById()));
       }
        return products;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody FakeStoreProductDto product) {
        Product productWithId = productService.addProduct(product);
        return productWithId;
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") int id, @RequestBody FakeStoreProductDto product) {
        Product product_ = productService.updateProduct(id, product);
        return product_;
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
    }

}

class ProductsComparatorById implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        return String.valueOf(o1.getId()).compareTo(String.valueOf(o2.getId()));
    }
}
