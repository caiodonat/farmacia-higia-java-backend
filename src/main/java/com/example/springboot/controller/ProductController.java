package com.example.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.model.Product;
import com.example.springboot.repository.ProductRepository;

@RestController
public class ProductController {

    private final ProductRepository repository;

    ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    Map<Long, Product> empData = new HashMap<Long, Product>();

    // @GetMapping("/products") String fakeCreate() {

    // Product product = new Product("drug", "dipirona", "123123123123", 8.54,
    // 10.0);

    // // repository.save(product);
    // return product.toString();
    // }

    @GetMapping("/products/save")
    String create() {

        Product product = new Product(
                "drug",
                "dipirona",
                "123123123123",
                8.54,
                11.0d);

        repository.save(product);
        return product.toString();
    }

    @GetMapping("/products/{id}")
    Product findById(@PathVariable Integer id) {
        // Product findById(@RequestParam Integer id) {

        Product product = new Product();
        Product productRes = repository.findById(id);

        product.setId(productRes.getId());
        product.setDescription(productRes.getDescription());

        System.out.println(product);

        empData.put(product.getId(), product);

        return product;
    }

    @GetMapping("/products/1")
    public ResponseEntity<Object> getData() {
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}