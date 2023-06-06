package com.farmaciahigia.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.farmaciahigia.model.Product;
import com.farmaciahigia.model.Storage;

public interface StorageRepository extends CrudRepository<Storage, Long> {

    Product findById(Integer id);

    List<Storage> findAll();

    // List<Product> findByType(String type);
}
/**
 * DELETE / URL/products
 * body:{
 *  products: [ 1, 2, 53, 23 ]
 * }
 * 
 * 
 * content: {
 *  name: "Asdas",
 *  urlImagem: "https://farmaciahigia.com/v1/files/uuid"
 * }
 */
