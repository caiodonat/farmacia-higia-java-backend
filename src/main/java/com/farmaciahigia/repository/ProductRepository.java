package com.farmaciahigia.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.farmaciahigia.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findById(Integer id);

    List<Product> findAll();
}
