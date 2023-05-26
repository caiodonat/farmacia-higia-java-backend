package com.example.springboot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.model.Product;
import com.example.springboot.repository.ProductRepository;

@RestController
public class ProductController {

    private final ProductRepository repository;

    ProductController(ProductRepository repository) {
        this.repository = repository;
    }
    // @GetMapping("/products") String fakeCreate() {

    //     Product product = new Product("drug", "dipirona", "123123123123", 8.54, 10.0);

    //     // repository.save(product);
    //     return product.toString();
    // }

    @GetMapping("/products/save") String create() {

        Product product = new Product("drug", "dipirona", "123123123123", 8.54, 11.0d);

        repository.save(product);
        return product.toString();
    }

    @GetMapping("/products/123123123123") String find() {

        Product product = repository.findByEan("123123123123");
        return product.toString();
    }
}