package com.farmaciahigia.repository;

import java.util.List;

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
	 *       [ ] encrypt password if not
	 */
	public Customer create(Customer customer) {

		Customer c = repository.save(customer);
		c = repository.findById(Long.valueOf(c.getId()))
				.get();

		return c;
	}

	public List<Customer> selectAll() {
		List<Customer> customers = repository.findAll();

		return customers;
	}

	public Customer selectByEmail(String email) {
		Customer customer = repository.findByEmail(email);

		return customer;
	}

	// List<Customer> findByLastName(String lastName);

	// Customer findByEmail(String email);
}