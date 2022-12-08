package com.richa.restconsumers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.richa.dtos.BookDTO;
import com.richa.entities.Book;

public class RestBookService {
	public static final String GET_BY_ISBN="http://localhost:2289/books?isbn=";
	static RestTemplate restTemplate=new RestTemplate();
//	public static void main(String[] args) {
//		getByIsbn("1234567891");
//	}
	private static Optional<BookDTO> findBookByISBN(String isbn) {
		ResponseEntity<BookDTO>response=restTemplate.getForEntity(GET_BY_ISBN+isbn, BookDTO.class);
		BookDTO book=response.getBody();
		System.out.println(book);
		return  ;
	}
}
