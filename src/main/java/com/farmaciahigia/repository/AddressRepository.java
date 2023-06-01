package com.farmaciahigia.repository;

import org.springframework.data.repository.CrudRepository;

import com.farmaciahigia.model.Address;

public interface AddressRepository extends CrudRepository<Address, Long>{
    
    Address findById(Integer id);
}
