package com.jacky.demo.config;

import com.jacky.demo.repository.ProductRepository;
import com.jacky.demo.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public ProductService productService(ProductRepository repository) {
        return new ProductService(repository);
    }

}
