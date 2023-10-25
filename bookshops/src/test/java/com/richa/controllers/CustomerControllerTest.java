//package com.richa.controllers;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.richa.dtos.BookDTO;
//import com.richa.dtos.CustomerDTO;
//import com.richa.entities.Customer;
//import com.richa.facades.CustomerFacade;
//import com.richa.repositories.CustomerRepository;
//import com.richa.services.CustomerService;
//
//@SpringBootTest(classes= {CustomerController.class})
//public class CustomerControllerTest{
//	
//	@Autowired
//	private CustomerController customerController;
//	
//	@MockBean
//	private CustomerFacade customerFacade;
//	
//	@Test
//	void addCustomerTest() throws Exception{
//		CustomerDTO cust=new CustomerDTO();
//		cust.setName("Isha");
//		cust.setSurname("Patel");
//		cust.setEmail("isha@gmail.com");
//		cust.setAddress("Bangalore");
//		when(customerFacade.addCustomer(cust)).thenReturn(cust);
//		ResponseEntity<CustomerDTO> response=customerController.addNewCustomer(cust);
//		CustomerDTO customerDTO1=response.getBody();
//		
//		assertEquals(customerDTO1.getEmail(),cust.getEmail());
//	}
//	private Customer convertToCustomer(CustomerDTO customerDTO) {
//    	Customer customer=new Customer();
//    	customer.setId(customerDTO.getId());
//    	customer.setPurchases(customerDTO.getPurchases());
//    	customer.setAddress(customerDTO.getAddress());
//    	customer.setEmail(customerDTO.getEmail());
//    	customer.setName(customerDTO.getName());
//    	customer.setSurname(customerDTO.getSurname());
//		return customer;
//    }
//	
//	@Test
//	void findCustomerByEmailTest() throws Exception{
//		CustomerDTO customerDTO=new CustomerDTO();
//		customerDTO.setName("Isha");
//		customerDTO.setSurname("Patel");
//		customerDTO.setEmail("isha@gmail.com");
//		customerDTO.setAddress("Bangalore");
//		Customer customer=convertToCustomer(customerDTO);
//		Optional<Customer>customerOptional=Optional.of(customer);
//		when(customerFacade.findCustomerByEmail(anyString())).thenReturn(Optional.of(customerDTO));
//		ResponseEntity<CustomerDTO> response=customerController.findCustomerByEmail(anyString());
//		CustomerDTO customerDTO1=response.getBody();
//		assertEquals(customerDTO1.getEmail(),customerDTO.getEmail());
//	}
//	
//	@Test
//	void getAllTest() throws Exception{
//		List<CustomerDTO> list=new ArrayList<CustomerDTO>();
//		CustomerDTO customerDTO=new CustomerDTO();
//		customerDTO.setName("Isha");
//		customerDTO.setSurname("Patel");
//		customerDTO.setEmail("isha2@gmail.com");
//		customerDTO.setAddress("Bangalore");
//		list.add(customerDTO);
//		when(customerFacade.findAll()).thenReturn(list);
//		ResponseEntity<List<CustomerDTO>> response=customerController.getAll();
//		List<CustomerDTO> customerDTO1=response.getBody();
//		CustomerDTO customer1=customerDTO1.get(0);
//		assertEquals(customerDTO.getEmail(),customer1.getEmail());
//		
//	}
//    
//	
//	
//}
//
