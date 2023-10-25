package com.richa.controllers;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.richa.dtos.BookDTO;
import com.richa.facades.BookFacade;

@SpringBootTest(classes= {BookController.class})
public class BookControllerTest{
	
	@Autowired
	private BookController bookController;
	
	@MockBean
	private BookFacade bookFacade;
	
	@Test
	void addBookTest() throws Exception{
		BookDTO bookDTO=new BookDTO("1234567890","RestTemplate",170.14,"RamShankar", 300,"RestTemplate for advance");
//		Book book= convertToBook(bookDTO);
		when(bookFacade.addBook(bookDTO)).thenReturn(bookDTO);
		ResponseEntity<BookDTO> response=bookController.addBook(bookDTO);
		BookDTO bookDTO1= response.getBody();
		assertEquals(bookDTO1,bookDTO);
	}
	
//	@Test
//	void getAllTest() throws Exception{
//		List<BookDTO> list=new ArrayList<BookDTO>();
//		BookDTO bookDTO=new BookDTO("1234567890","RestTemplate",170.14,"RamShankar", 300,"RestTemplate for advance");
//		list.add(bookDTO);
//		when(bookFacade.findAll()).thenReturn(list);
//		ResponseEntity<List<BookDTO>> booklist=bookController.getAll();
//		List<BookDTO> response=booklist.getBody();
//		BookDTO book1=response.get(0);
//		assertEquals(book1.getISBN(),bookDTO.getISBN());
//		}
	
	@Test
	void getBookByIsbnTest() throws Exception{
		BookDTO bookDTO=new BookDTO("1234567890","RestTemplate",170.14,"RamShankar", 300,"RestTemplate for advance");
		when(bookFacade.findBookByISBN(anyString())).thenReturn(bookDTO);
		ResponseEntity<BookDTO> response=bookController.getBookByISBN(anyString());
		BookDTO bookDTO1= response.getBody();
		assertEquals(bookDTO1.getISBN(),bookDTO.getISBN());
	}
	
	@Test
	void getBookByIdTest() throws Exception{
		BookDTO bookDTO=new BookDTO(3l,"1234567890","RestTemplate",170.14,"RamShankar", 300,"RestTemplate for advance");
		when(bookFacade.findBookById(2l)).thenReturn(bookDTO);
		ResponseEntity<BookDTO> response=bookController.getBookById(2l);
		BookDTO bookDTO1= response.getBody();
		assertEquals(bookDTO1.getISBN(),bookDTO.getISBN());
	}
	@Test
	void getBooksByTitle() throws Exception{
		List<BookDTO> list=new ArrayList<BookDTO>();
		BookDTO bookDTO=new BookDTO("1234567890","RestTemplate",170.14,"RamShankar", 300,"RestTemplate for advance");
		list.add(bookDTO);
		when(bookFacade.findBooksByTitle(anyString())).thenReturn(list);
		ResponseEntity<List<BookDTO>> booklist=bookController.getBooksByTitle(anyString());
		List<BookDTO> response=booklist.getBody();
		BookDTO book1=response.get(0);
		assertEquals(book1.getISBN(),bookDTO.getISBN());
			}
	@Test
	void updateBookTest() throws Exception{
		BookDTO bookDTO=new BookDTO(3l,"1234567890","RestTemplate",170.14,"RamShankar", 300,"RestTemplate for advance");
		when(bookFacade.updateBook(bookDTO, 3l)).thenReturn(bookDTO);
		ResponseEntity<BookDTO> response=bookController.updateBook(bookDTO);
		BookDTO bookDTO1= response.getBody();
		assertEquals(bookDTO1.getISBN(),bookDTO.getISBN());
	}
	
	@Test
	void deleteBookTest() throws Exception{
		BookDTO bookDTO=new BookDTO(3l,"1234567890","RestTemplate",170.14,"RamShankar", 300,"RestTemplate for advance");
		Mockito.doNothing().when(bookFacade).deleteBookByIsbn(anyString());
		bookController.deleteBookByIsbn(anyString());
		verify(bookFacade).deleteBookByIsbn(anyString());
	}
}