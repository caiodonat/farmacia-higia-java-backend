package com.farmaciahigia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.farmaciahigia.dao.ProductDao;
import com.farmaciahigia.model.Product;

@Service
public class ProductRepositoryNew {
    
    private final ProductDao repository;
    public ProductRepositoryNew(ProductDao repository) {
        this.repository = repository;
    }
    
    public List<Product> selectByType(String type){
        System.out.println(type);
        
        return repository.findAll()
        // .removeIf(p -> p.getType().equalsIgnoreCase(type))
        .stream()
        .filter(p -> (
            p.getType().contains(type)
        ))
        .toList()
        ;
        
    }
    
    
}