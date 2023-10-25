package com.richa.facades;

import com.richa.dtos.BookDTO;
import com.richa.dtos.CustomerDTO;
import com.richa.dtos.PurchaseRequestDTO;
import com.richa.dtos.PurchaseResponseDTO;
import com.richa.entities.Book;
import com.richa.entities.Customer;
import com.richa.entities.Purchase;
import com.richa.entities.enums.PaymentMethod;
import com.richa.exception.global.customexceptions.BookCreateException;
import com.richa.exception.global.customexceptions.CustomerCreateException;
import com.richa.exception.global.customexceptions.PaymentException;
import com.richa.restconsumers.RestBookService;
//import com.richa.services.BookService;
import com.richa.services.CustomerService;
import com.richa.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.richa.constants.Currency.GBP;
import static com.richa.constants.ErrorCode.*;

@Service
public class PurchaseFacade {

    @Autowired
    private PurchaseService purchaseService;

//    @Autowired
 //  private BookService bookService;
//    @Autowired
//    private RestBookService restBookService;

//    @Autowired
//    private CustomerService customerService;
    @Autowired
    private CustomerFacade customerFacade;
    
//    public PurchaseFacade(RestBookService restBookService) {
//		super();
//		this.restBookService = restBookService;
//	}
    public PurchaseFacade() {
    }

	public PurchaseResponseDTO performPurchase(PurchaseRequestDTO purchaseRequestDTO) throws Exception,BookCreateException, CustomerCreateException,PaymentException {
		RestTemplate restTemplate =new RestTemplate();
    	RestBookService restBookService=new RestBookService(restTemplate);
		PurchaseResponseDTO purchaseResponseDTO = new PurchaseResponseDTO();
        Optional<BookDTO> bookOptional = restBookService.findBookByISBN(purchaseRequestDTO.getBookISBN());
        System.out.println("BookService called");
        if(!bookOptional.isPresent()) {
        	String errMsg="Book is not there!";
        	throw new BookCreateException(errMsg,HttpStatus.BAD_REQUEST.value());
        }
        System.out.println("BookDtO....");
       System.out.println(bookOptional.get());
        Optional<CustomerDTO> customerOptional = customerFacade.findCustomerByEmail(purchaseRequestDTO.getCustomerEmail());
//        System.out.println("Customer Service called");
        if(!customerOptional.isPresent()) {
        	String errMsg="Customer is not Present!";
        	throw new CustomerCreateException(errMsg,HttpStatus.BAD_REQUEST.value());
        }
        System.out.println("CustomerDTO...");
       System.out.println(customerOptional.get());
        Optional<PaymentMethod> paymentMethodOptional = PaymentMethod.fromValue(purchaseRequestDTO.getPaymentMethod());
//        System.out.println("Fetched Payment Method");
        if(!paymentMethodOptional.isPresent()) {
        	String errMsg="Payment Method is invalid";
        	throw new PaymentException(errMsg,HttpStatus.BAD_REQUEST.value());
        }
        System.out.println("Payment Method..");
        System.out.println(paymentMethodOptional.get());
        Customer customer = convertToCustomer(customerOptional.get());
        BookDTO bookDTO = bookOptional.get();//getting from other app
//        Book book= convertToBook(bookDTO);
       
        PaymentMethod paymentMethod = paymentMethodOptional.get();
        
        Purchase purchase = new Purchase();
//   
                   purchase.setCustomer(customer);
                    purchase.setPrice(bookDTO.getPrice());
                    purchase.setBook(bookDTO.getId());//will check the relationship
                    purchase.setPaymentMethod(paymentMethod);
                    purchase.setCurrency(GBP.name());
//                .build();
        purchase=purchaseService.save(purchase);
        System.out.println("purchase done ");
        System.out.println(purchase);
        purchaseResponseDTO = PurchaseResponseDTO.fromPurchase(purchase);//FETCHING SAVED PURCHASE AND CONVERTING TO RESPONSEDTO
        System.out.println("purchase response done");
        System.out.println(purchaseResponseDTO);
        return purchaseResponseDTO;
    }

    public List<PurchaseResponseDTO> findPurchasesByCustomerEmail(String email) throws Exception{
    	
    	List<Purchase> purchaseResponseDTO=purchaseService.findPurchasesByCustomerEmail(email);
    	if(!purchaseResponseDTO.isEmpty()) {

    		return purchaseService.findPurchasesByCustomerEmail(email).stream().map(PurchaseResponseDTO::fromPurchase).collect(Collectors.toList());
    	}
    	else {
    		String err="Customer not found with the id";
    		throw new Exception(err);
    	}
 
    	
    }
    private Book convertToBook(BookDTO bookDTO) {
    	Book book = new Book();
		book.setIsbn(bookDTO.getISBN());
		book.setTitle(bookDTO.getTitle());
		book.setPrice(bookDTO.getPrice());
		book.setAuthor(bookDTO.getAuthor());
		book.setPages(bookDTO.getPages());
		book.setProvider(bookDTO.getProvider());
		book.setId(bookDTO.getId());
		return book;
    }
    private Customer convertToCustomer(CustomerDTO customerDTO) {
    	Customer customer=new Customer();
    	customer.setId(customerDTO.getId());
    	customer.setPurchases(customerDTO.getPurchases());
    	customer.setAddress(customerDTO.getAddress());
    	customer.setEmail(customerDTO.getEmail());
    	customer.setName(customerDTO.getName());
    	customer.setSurname(customerDTO.getSurname());
		return customer;
    }
}
