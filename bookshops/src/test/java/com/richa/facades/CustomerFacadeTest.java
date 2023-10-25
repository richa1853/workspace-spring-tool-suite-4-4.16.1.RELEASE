//package com.richa.facades;
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
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.ResponseEntity;
//
//import com.richa.controllers.CustomerController;
//import com.richa.dtos.CustomerDTO;
//import com.richa.entities.Customer;
//import com.richa.exception.global.customexceptions.BookCreateException;
//import com.richa.exception.global.customexceptions.CustomerCreateException;
//import com.richa.services.CustomerService;
//
//@SpringBootTest(classes= {CustomerFacade.class})
//public class CustomerFacadeTest {
//	
//	@Autowired
//	private CustomerFacade customerFacade;
//	
//	@MockBean
//	private CustomerService customerService;
//	
//	//public final ExpectedException exception = ExpectedException.none();
//	
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
//	
//	@Test
//	void addCustomerExistingCustomerTest() throws Exception{
//		
//		CustomerDTO customerDTO=new CustomerDTO();
//		customerDTO.setName("Isha");
//		customerDTO.setSurname("Patel");
//		customerDTO.setEmail("isha@gmail.com");
//		customerDTO.setAddress("Bangalore");
//		customerDTO.setId(2l);
//		
//		Customer customer=convertToCustomer(customerDTO);
//		Optional<Customer>customerOptional=Optional.of(customer);
//		
//		when(customerService.findCustomerByEmail(anyString())).thenReturn(customerOptional);
//		
//		Assertions.assertThatExceptionOfType(CustomerCreateException.class).isThrownBy(() -> {
//			customerFacade.addCustomer(customerDTO);
//		    }).withMessage("Customer already exists");
//	}
//	@Test
//	void addCustomerTest() throws Exception{
//		Optional<Customer>customerOptional=Optional.empty();
//		
//		CustomerDTO customerDTO1=new CustomerDTO();
//		customerDTO1.setName("Isha");
//		customerDTO1.setSurname("Patel");
//		customerDTO1.setEmail("isha@gmail.com");
//		customerDTO1.setAddress("Bangalore");
//		customerDTO1.setId(3l);
//		
//		Customer customer1=convertToCustomer(customerDTO1);
//		Optional<Customer>customerOptional1=Optional.of(customer1);
//		
//		when(customerService.findCustomerByEmail(anyString())).thenReturn(customerOptional,customerOptional1);
//		CustomerDTO newCustomer=customerFacade.addCustomer(customerDTO1);
//		assertEquals(newCustomer.getId(),customerDTO1.getId());
//	}
//	
//	@Test
//	void findCustomerByEmail() throws Exception{
//		CustomerDTO customerDTO=new CustomerDTO();
//		customerDTO.setName("Isha");
//		customerDTO.setSurname("Patel");
//		customerDTO.setEmail("isha@gmail.com");
//		customerDTO.setAddress("Bangalore");
//		customerDTO.setId(2l);
//		
//		Customer customer=convertToCustomer(customerDTO);
//		Optional<Customer>customerOptional=Optional.of(customer);
//		
//		when(customerService.findCustomerByEmail(anyString())).thenReturn(customerOptional);
//		Optional<CustomerDTO> customerDTO1=customerFacade.findCustomerByEmail(anyString());
//		CustomerDTO customer1=customerDTO1.get();
//		assertEquals(customer1.getId(),customerDTO.getId());
//		}
//	@Test
//	void findCustomerNotPresentTest() throws Exception{
//		Optional<Customer>customerOptional=Optional.empty();
//		CustomerDTO customerDTO=new CustomerDTO();
//		customerDTO.setName("Isha");
//	    customerDTO.setSurname("Patel");
//		customerDTO.setAddress("Bangalore");
//		
//		Customer customer=convertToCustomer(customerDTO);
//		Optional<Customer>customerOptional1=Optional.of(customer);
//		
//		when(customerService.findCustomerByEmail(anyString())).thenReturn(customerOptional);
//		
//		Assertions.assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
//			customerFacade.findCustomerByEmail(anyString());
//		    }).withMessage("Customer is not there");
//		}
//	
//	@Test
//	void findallTest() {
//		List<Customer> list=new ArrayList<Customer>();
//		CustomerDTO customerDTO=new CustomerDTO();
//		customerDTO.setName("Isha");
//		customerDTO.setSurname("Patel");
//		customerDTO.setEmail("isha2@gmail.com");
//		customerDTO.setAddress("Bangalore");
//		
//		Customer customer=convertToCustomer(customerDTO);
//		list.add(customer);
//		
//		when(customerService.findAll()).thenReturn(list);
//		List<CustomerDTO> response=customerFacade.findAll();
//		CustomerDTO customer1=response.get(0);
//		assertEquals(customer1.getEmail(),customer.getEmail());
//	}
//		
//	}
//	
//	
//
//
//
//
