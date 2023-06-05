package com.farmaciahigia.repository;

import org.springframework.data.repository.CrudRepository;

import com.farmaciahigia.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{
    
    Employee findById(Integer id);
}
