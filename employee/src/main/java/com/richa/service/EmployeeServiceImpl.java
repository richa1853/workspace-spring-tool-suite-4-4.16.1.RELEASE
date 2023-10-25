package com.richa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richa.entity.Employee;
import com.richa.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired 
	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@Transactional
	@Override
	public Employee addEmployee(Employee employee) {
//		Employee emp=new Employee();
		employee.setName(employee.getName());
		employee.setId(employee.getId());
		employee.setAddress(employee.getAddress());
		employeeRepository.save(employee);
		return employee;
	}
	@Override
	public List<Employee> findAll(){
		   List<Employee> employees = new ArrayList<Employee>();
		   employeeRepository.findAll().forEach(employee -> employees.add(employee));
		return employees;// check with this.
	}

	@Override
	public Employee findByEmpId(Long id) {
		// TODO Auto-generated method stub
		Optional<Employee> emp= employeeRepository.findById(id);
		return emp.get();
	}

	@Transactional
	@Override
	public void deleteEmployee(Long id) {
		employeeRepository.deleteEmployeeById(id);
	}
	
	

}
