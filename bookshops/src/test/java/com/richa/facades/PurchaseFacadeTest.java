//package com.richa.facades;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import com.richa.controllers.PurchaseController;
//import com.richa.dtos.BookDTO;
//import com.richa.dtos.CustomerDTO;
//import com.richa.dtos.PurchaseRequestDTO;
//import com.richa.dtos.PurchaseResponseDTO;
//import com.richa.entities.Book;
//import com.richa.entities.Customer;
//import com.richa.entities.Purchase;
//import com.richa.restconsumers.RestBookService;
//import com.richa.services.PurchaseService;
//
//@SpringBootTest(classes= {PurchaseFacade.class})
//public class PurchaseFacadeTest {
//
//	@Autowired
//	private PurchaseFacade purchaseFacade;
//	
//	@MockBean
//	private PurchaseService purchaseService;
//	
//	@MockBean
//	private CustomerFacade customerFacade;
//	
//	@MockBean
//	private RestBookService restBookService;
//	
//	
//	
//	 private Book convertToBook(BookDTO bookDTO) {
//	    	Book book = new Book();
//			book.setIsbn(bookDTO.getISBN());
//			book.setTitle(bookDTO.getTitle());
//			book.setPrice(bookDTO.getPrice());
//			book.setAuthor(bookDTO.getAuthor());
//			book.setPages(bookDTO.getPages());
//			book.setProvider(bookDTO.getProvider());
//			book.setId(bookDTO.getId());
//			return book;
//	    }
//	    private Customer convertToCustomer(CustomerDTO customerDTO) {
//	    	Customer customer=new Customer();
//	    	customer.setId(customerDTO.getId());
//	    	customer.setPurchases(customerDTO.getPurchases());
//	    	customer.setAddress(customerDTO.getAddress());
//	    	customer.setEmail(customerDTO.getEmail());
//	    	customer.setName(customerDTO.getName());
//	    	customer.setSurname(customerDTO.getSurname());
//			return customer;
//	    }
//	
//
//	
//	
//	
//}
