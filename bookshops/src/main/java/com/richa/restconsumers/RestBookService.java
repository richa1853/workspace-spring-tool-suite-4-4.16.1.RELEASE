package com.richa.restconsumers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.@Autowired(required=true);
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.richa.dtos.BookDTO;
//import com.richa.facades.PurchaseFacade;

@Service
public class RestBookService {
//	public static final String ADD_CUSTOMER="http://localhost:2290/customers";
//	public static final String Add_PURCHASE="http://localhost:2290/purchases";
    private static final String GET_BY_ISBN = "http://localhost:2289/books?isbn=";
    private static final String GET_BY_ID= "http://localhost:2289/books?id=";

//    @Autowired
//	private final RestTemplate restTemplate;
//	
//	public RestBookService(RestTemplate restTemplate) {
//	super();
//	this.restTemplate = restTemplate;
//}
    static RestTemplate restTemplate=new RestTemplate();

	public static Optional<BookDTO> findBookByISBN(String isbn) {
	//ResponseEntity<BookDTO>response = restTemplate.getForEntity(GET_BY_ISBN+isbn, BookDTO.class);
		ResponseEntity<BookDTO>response =restTemplate.getForEntity(GET_BY_ISBN+isbn, BookDTO.class);
		Optional<BookDTO> bookDTO = Optional.of(response.getBody());
		System.out.println(bookDTO);

		return bookDTO;	
	}
	public static Optional<BookDTO> findBookById(long id) {
		//ResponseEntity<BookDTO>response = restTemplate.getForEntity(GET_BY_ISBN+isbn, BookDTO.class);
			ResponseEntity<BookDTO>response =restTemplate.getForEntity(GET_BY_ID+id, BookDTO.class);
			Optional<BookDTO> bookDTO = Optional.of(response.getBody());
			System.out.println(bookDTO);

			return bookDTO;	
		}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
	
	
	
	
}
