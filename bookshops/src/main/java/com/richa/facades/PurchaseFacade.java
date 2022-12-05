package com.richa.facades;

import com.richa.dtos.BookDTO;
import com.richa.dtos.CustomerDTO;
import com.richa.dtos.PurchaseRequestDTO;
import com.richa.dtos.PurchaseResponseDTO;
import com.richa.entities.Book;
import com.richa.entities.Customer;
import com.richa.entities.Purchase;
import com.richa.entities.enums.PaymentMethod;
import com.richa.restconsumers.RestBookService;
//import com.richa.services.BookService;
import com.richa.services.CustomerService;
import com.richa.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
//    private BookService bookService;// call bookService using restTemplate
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

	public PurchaseResponseDTO performPurchase(PurchaseRequestDTO purchaseRequestDTO) {
        PurchaseResponseDTO purchaseResponseDTO = new PurchaseResponseDTO();
        Optional<BookDTO> bookOptional = RestBookService.findBookByISBN(purchaseRequestDTO.getBookISBN());
        System.out.println("BookService called");
        if(!bookOptional.isPresent()) {
            purchaseResponseDTO.setErrorMessage(BOOK_NOT_FOUND.getMessage());
            purchaseResponseDTO.setError(BOOK_NOT_FOUND.getCode());
            return  purchaseResponseDTO;
        }
        System.out.println("BookDtO....");
       System.out.println(bookOptional.get());
        Optional<CustomerDTO> customerOptional = customerFacade.findCustomerByEmail(purchaseRequestDTO.getCustomerEmail());
        System.out.println("Customer Service called");
        if(!customerOptional.isPresent()) {
            purchaseResponseDTO.setErrorMessage(CUSTOMER_NOT_FOUND.getMessage());
            purchaseResponseDTO.setError(CUSTOMER_NOT_FOUND.getCode());
            return  purchaseResponseDTO;
        }
        System.out.println("CustomerDTO...");
       System.out.println(customerOptional.get());
        Optional<PaymentMethod> paymentMethodOptional = PaymentMethod.fromValue(purchaseRequestDTO.getPaymentMethod());
        System.out.println("Fetched Payment Method");
        if(!paymentMethodOptional.isPresent()) {
            purchaseResponseDTO.setErrorMessage(PAYMENT_METHOD_NOT_VALID.getMessage());
            purchaseResponseDTO.setError(PAYMENT_METHOD_NOT_VALID.getCode());
            return purchaseResponseDTO;
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

    public List<PurchaseResponseDTO> findPurchasesByCustomerEmail(String email){
    	return purchaseService.findPurchasesByCustomerEmail(email).stream().map(PurchaseResponseDTO::fromPurchase).collect(Collectors.toList());
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
