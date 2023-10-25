package com.richa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.richa.entity.Employee;

@Service
public interface EmployeeService {
	Employee addEmployee(Employee employee);
	List<Employee>findAll();
	Employee findByEmpId(Long id);
	void deleteEmployee(Long id);
	
}
