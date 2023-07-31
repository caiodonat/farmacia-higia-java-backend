package com.farmaciahigia.repository;

import org.springframework.stereotype.Service;

import com.farmaciahigia.dao.CustomerDao;
import com.farmaciahigia.model.Customer;

@Service
public class CustomerRepository {

	private final CustomerDao repository;

	public CustomerRepository(CustomerDao repository) {
		this.repository = repository;
	}

	public Customer selectById(Long id) {
		Customer p = repository.findById(id).get();
		return p;
	}

	/**
	 * @TODO
	 * [ ] encrypt password if not
	 */
	public Customer create(Customer customer) {

		Customer c = repository.save(customer);
		c = repository.findById(Long.valueOf(c.getId())).get();

		return c;
	}

	// List<Customer> findByLastName(String lastName);

	// List<Customer> findAll();

	// Customer findById(long id);

	// Customer findByEmail(String email);
}
