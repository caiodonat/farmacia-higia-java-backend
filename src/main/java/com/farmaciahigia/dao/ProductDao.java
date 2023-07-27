package com.farmaciahigia.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.farmaciahigia.model.Product;

@Service
public interface ProductDao extends CrudRepository<Product, Long> {
  List<Product> findAll();

  Product findById(long id);
}
