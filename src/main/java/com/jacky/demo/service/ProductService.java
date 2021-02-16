package com.jacky.demo.service;

import com.jacky.demo.auth.UserIdentity;
import com.jacky.demo.converter.ProductConverter;
import com.jacky.demo.entity.product.ProductRequest;
import com.jacky.demo.entity.product.ProductResponse;
import com.jacky.demo.exception.NotFoundException;
import com.jacky.demo.repository.ProductRepository;
import com.jacky.demo.entity.product.Product;
import com.jacky.demo.parameter.ProductQueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductService {

    private ProductRepository repository;

    @Autowired
    private UserIdentity userIdentity;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductResponse getProductResponse(String id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Can't find product."));
        return ProductConverter.toProductResponse(product);
    }

    public ProductResponse createProduct(ProductRequest request) {
        Product product = ProductConverter.toProduct(request);
        System.out.println("Here:"+ userIdentity.getName());
        product.setCreator(userIdentity.getName());
        product = repository.insert(product);

        return ProductConverter.toProductResponse(product);
    }

    public ProductResponse replaceProduct(String id, ProductRequest request) {
        Product oldProduct = getProduct(id);
        Product newProduct = ProductConverter.toProduct(request);
        newProduct.setId(oldProduct.getId());
        newProduct.setCreator(oldProduct.getCreator());

        repository.save(newProduct);

        return ProductConverter.toProductResponse(newProduct);
    }

    public void deleteProduct(String id) {
        repository.deleteById(id);
    }

    public Product getProduct(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Can't find product."));
    }

    public List<Product> getProducts(ProductQueryParameter param) {
        String nameKeyword = Optional.ofNullable(param.getKeyword()).orElse("");
        String orderBy = param.getOrderBy();
        String sortRule = param.getSortRule();

        Sort sort = Sort.unsorted();
        if (Objects.nonNull(orderBy) && Objects.nonNull(sortRule)) {
            Sort.Direction direction = Sort.Direction.fromString(sortRule);
            sort = Sort.by(direction, orderBy);
        }

        return repository.findByNameLike(nameKeyword, sort);
    }
}
