package com.scaler.fakestoreapplication.DTO;

import com.scaler.fakestoreapplication.model.Category;
import com.scaler.fakestoreapplication.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private String id;
    private String title;
    private String price;
    private String category;
    private String description;
    private String image;

    public Product toProduct()
    {
        Product product = new Product();
        product.setId(Integer.parseInt(id));
        product.setName(title);
        product.setDescription(description);
        product.setPrice(Double.parseDouble(price));
        product.setImageUrl(image);
        product.setCategory(
                new Category(category)
        );
        return product;
    }

    public String toString() {
        return "ProductResponseDTO [productId=" + id + ", productName="+title+", productDescription="+description+", productPrice="+price+", productImageUrl="+image+", productCategory="+category+"]";
    }
}
