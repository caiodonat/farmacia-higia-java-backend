package com.farmaciahigia.repository;

import org.springframework.data.repository.CrudRepository;

import com.farmaciahigia.model.Establishment;

public interface EstablishmentRepository extends CrudRepository<Establishment, Long>{
    
    Establishment findById(Integer id);
}
