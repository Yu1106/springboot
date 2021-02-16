package com.jacky.demo.entity.product;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ProductRequest {

    private String id;

    @NotEmpty(message = "Product name is undefined.")
    private String name;

    @Min(0)
    private int price;

    public ProductRequest() {

    }

    public ProductRequest(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
