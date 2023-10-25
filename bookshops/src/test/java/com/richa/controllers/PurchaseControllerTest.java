//package com.richa.controllers;
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
//import org.springframework.http.ResponseEntity;
//
//import com.richa.dtos.BookDTO;
//import com.richa.dtos.CustomerDTO;
//import com.richa.dtos.PurchaseRequestDTO;
//import com.richa.dtos.PurchaseResponseDTO;
//import com.richa.facades.PurchaseFacade;
//
//@SpringBootTest(classes= {PurchaseController.class})
//public class PurchaseControllerTest {
//	
//	@Autowired
//	PurchaseController purchaseController;
//	
//	@MockBean
//	PurchaseFacade purchaseFacade;
//	
//	@Test
//	void addPurchaseTest() throws Exception{
//		PurchaseRequestDTO purchaseRequestDTO=new PurchaseRequestDTO();
//		purchaseRequestDTO.setBookISBN("1234567890");
//		purchaseRequestDTO.setCustomerEmail("richa@gmail.com");
//		purchaseRequestDTO.setPaymentMethod("Cash");
//		BookDTO bookDTO=new BookDTO("1234567890","RestTemplate",170.14,"RamShankar", 300,"RestTemplate for advance");
//		CustomerDTO customerDTO=new CustomerDTO("Richa","Raj","Bangalore","richa@gmail.com");
//	
//		PurchaseResponseDTO purchaseResponseDTO=new PurchaseResponseDTO();
//		purchaseResponseDTO.setBook(bookDTO);
//		purchaseResponseDTO.setCustomer(customerDTO);
//		purchaseResponseDTO.setPrice((double) 130);
//		purchaseResponseDTO.setCurrency("Rs");
//		purchaseResponseDTO.setId(4l);
//		
//		
//		when(purchaseFacade.performPurchase(purchaseRequestDTO)).thenReturn(purchaseResponseDTO);
//		ResponseEntity<PurchaseResponseDTO> response=purchaseController.addPurchase(purchaseRequestDTO);
//		PurchaseResponseDTO purchaseResponseDTO1=response.getBody();
//		assertEquals(purchaseResponseDTO1.getId(),purchaseResponseDTO.getId());
//	}
//	
//	@Test
//	void findPurchasesByCustomerEmailTest() throws Exception{
//		List<PurchaseResponseDTO> list=new ArrayList<PurchaseResponseDTO>();
//		
//		BookDTO bookDTO=new BookDTO("1234567890","RestTemplate",170.14,"RamShankar", 300,"RestTemplate for advance");
//			CustomerDTO customerDTO=new CustomerDTO("Richa","Raj","Bangalore","richa@gmail.com");
//		
//			PurchaseResponseDTO purchaseResponseDTO=new PurchaseResponseDTO();
//			purchaseResponseDTO.setBook(bookDTO);
//			purchaseResponseDTO.setCustomer(customerDTO);
//			purchaseResponseDTO.setPrice((double) 130);
//			purchaseResponseDTO.setCurrency("Rs");
//			purchaseResponseDTO.setId(4l);
//			
//			list.add(purchaseResponseDTO);
//			
//			when(purchaseFacade.findPurchasesByCustomerEmail(anyString())).thenReturn(list);
//			ResponseEntity<List<PurchaseResponseDTO>> response=purchaseController.findPurchasesByCustomerEmail(anyString());
//			List<PurchaseResponseDTO> purchaseResponseDTO1=response.getBody();
//			PurchaseResponseDTO purchase1=purchaseResponseDTO1.get(0);
//			assertEquals(purchase1.getId(),purchaseResponseDTO.getId());
//	}	
//}
