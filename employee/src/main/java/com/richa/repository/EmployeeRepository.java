package com.richa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.richa.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	List<Employee> findAll();
//	Optional<Employee> findByEmpId(long id);
	void deleteEmployeeById(long id);
	
}
