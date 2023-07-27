package com.farmaciahigia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.farmaciahigia.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
    
    @Query("SELECT p FROM product p WHERE p.id=?1")
    Product findById(Integer id);

    @Query("SELECT p FROM product p")
    List<Product> findAll();

    @Query("SELECT p FROM product p WHERE p.type LIKE ?1")
    List<Product> selectByType(String type);

    @Query("SELECT p FROM product p WHERE p.value=?1")
    Product selectByValue(Double value);

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
