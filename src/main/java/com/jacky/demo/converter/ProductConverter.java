package com.jacky.demo.converter;

import com.jacky.demo.entity.product.Product;
import com.jacky.demo.entity.product.ProductRequest;
import com.jacky.demo.entity.product.ProductResponse;

public class ProductConverter {

    private ProductConverter() {

    }

    public static Product toProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setCreator(product.getCreator());

        return product;
    }

    public static ProductResponse toProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setCreator(product.getCreator());

        return response;
    }
}
