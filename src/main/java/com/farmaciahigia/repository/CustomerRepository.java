package com.farmaciahigia.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.farmaciahigia.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

  List<Customer> findByLastName(String lastName);

  List<Customer> findAll();

  Customer findById(long id);

  Customer findByEmail(String email);
}
