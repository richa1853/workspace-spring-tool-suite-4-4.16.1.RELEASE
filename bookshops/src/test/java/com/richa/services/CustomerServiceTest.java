//package com.richa.services;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import com.richa.entities.Customer;
//import com.richa.repositories.CustomerRepository;
//
//@SpringBootTest(classes= {CustomerService.class})
//public class CustomerServiceTest {
//	@Autowired
//	private CustomerService customerService;
//	
//	@MockBean
//	private CustomerRepository customerRepository;
//	
//	@Test
//	void saveTest() throws Exception{
//		Customer cust=new Customer();
//		cust.setName("Isha");
//		cust.setSurname("Patel");
//		cust.setEmail("isha@gmail.com");
//		cust.setAddress("Bangalore");
//		when(customerRepository.save(cust)).thenReturn(cust);
//		Customer customer1=customerService.save(cust);
//		assertEquals(customer1.getEmail(),cust.getEmail());
//	}
//	
//	@Test
//	void findCustomerByEmailTest() throws Exception{
//		Customer cust=new Customer();
//		cust.setName("Isha");
//		cust.setSurname("Patel");
//		cust.setEmail("isha@gmail.com");
//		cust.setAddress("Bangalore");
//		cust.setId(2l);
//		
//		Optional<Customer>customerOptional=Optional.of(cust);
//		
//		when(customerRepository.findCustomerByEmail(anyString())).thenReturn(customerOptional);
//		Optional<Customer> customer1=customerService.findCustomerByEmail(anyString());
//		Customer customer=customer1.get();
//		assertEquals(customer.getEmail(),cust.getEmail());
//	}
//	
//	@Test
//	void findAllCustomerTest()throws Exception{
//		List<Customer> list=new ArrayList<Customer>();
//		Customer customer=new Customer();
//		customer.setName("Isha");
//		customer.setSurname("Patel");
//		customer.setEmail("isha2@gmail.com");
//		customer.setAddress("Bangalore");
//		
//		list.add(customer);
//		
//		when(customerRepository.findAll()).thenReturn(list);
//		List<Customer> response=customerService.findAll();
//		Customer customer1=response.get(0);
//		assertEquals(customer1.getEmail(),customer.getEmail());
//	}
//
//}
