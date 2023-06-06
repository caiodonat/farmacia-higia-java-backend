package com.farmaciahigia.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.farmaciahigia.model.Product;
import com.farmaciahigia.model.Storage;

public interface StorageRepository extends CrudRepository<Storage, Long> {

    Product findById(Integer id);

    List<Storage> findAll();
}
