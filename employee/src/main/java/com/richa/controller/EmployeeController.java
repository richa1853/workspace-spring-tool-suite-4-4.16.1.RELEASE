 package com.richa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.richa.entity.Employee;
import com.richa.service.EmployeeService;
import com.richa.service.EmployeeServiceImpl;

@RestController
@RequestMapping(value = "/employee", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}) 
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	@PostMapping("/add")
	public Employee addEmployee(@RequestBody Employee employee){
		return  employeeService.addEmployee(employee);
		
	}
	@GetMapping
	public List<Employee> findAll(){
		return employeeService.findAll();
	}
	
	@GetMapping("/id")
	public Employee findEmployeeById(long id) {
		return employeeService.findByEmpId(id);
	}
	@DeleteMapping(params = "id")
	public void deleteBookById(@RequestParam("id") long id) {
		employeeService.deleteEmployee(id);
	}
	
	
	

}
