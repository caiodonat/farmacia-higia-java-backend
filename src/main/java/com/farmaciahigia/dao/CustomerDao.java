package com.farmaciahigia.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.farmaciahigia.model.Customer;

@Service
public interface CustomerDao extends CrudRepository<Customer, Long> {
}
