package com.example.springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.springboot.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findById(Integer id);
}
