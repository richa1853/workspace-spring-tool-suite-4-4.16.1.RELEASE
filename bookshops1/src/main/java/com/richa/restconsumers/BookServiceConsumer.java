package com.richa.restconsumers;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.richa.dtos.BookDTO;
import com.richa.entities.Book;

public class BookServiceConsumer {
	public static final String ADD_BOOKS_API="http://localhost:2289/books";
	public static final String GET_ALL="http://localhost:2289/books";
//	public static final String GET_BY_ISBN="http://localhost:2289/books?isbn=";
	
	
	static RestTemplate restTemplate=new RestTemplate();
	public static void main(String[] args) {
		addBooks(new BookDTO("1234567891","RestTemplate",170.14,"RamShankar", 300,"RestTemplate for advance"));
		addBooks(new BookDTO("1234567892","RestTemplate1",180.14,"RamShankar1", 400,"RestTemplate1 for advance"));
		addBooks(new BookDTO("1234567893","RestTemplate2",180.14,"RamShankar2", 400,"RestTemplate2 for advance"));
		addBooks(new BookDTO("1234567894","RestTemplate3",180.14,"RamShankar3", 400,"RestTemplate3 for advance"));
		getAll();
//		getByIsbn("1234567891");
	}
	
	private static void addBooks(BookDTO bookDTO) {
		HttpEntity<BookDTO> request=new HttpEntity<BookDTO>(bookDTO);
		
		BookDTO bookCreateResponse=restTemplate.postForObject(ADD_BOOKS_API, request, BookDTO.class);
		System.out.println(bookCreateResponse);	
	}
	private static void getAll() {
		ResponseEntity<List>response=restTemplate.getForEntity(GET_ALL, List.class);
		List<BookDTO>books=response.getBody();
		System.out.println(books);
	}
//	private static void getByIsbn(String isbn) {
//		ResponseEntity<BookDTO>response=restTemplate.getForEntity(GET_BY_ISBN+isbn, BookDTO.class);
//		BookDTO book=response.getBody();
//		System.out.println(book);
//		
//	}
}
