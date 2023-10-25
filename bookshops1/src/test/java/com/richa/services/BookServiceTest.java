package com.richa.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.richa.dtos.BookDTO;
import com.richa.entities.Book;
import com.richa.repositories.BookRepository;

@SpringBootTest(classes= {BookService.class})
public class BookServiceTest {
	@Autowired
	private BookService bookService;
	@MockBean
	private BookRepository bookRepository;
	
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
	
	void SaveTest() throws Exception{
		BookDTO bookDTO=new BookDTO("1234567890","RestTemplate",170.14,"RamShankar", 300,"RestTemplate for advance");
		Book book= convertToBook(bookDTO);
		book.setId(2l);
		when(bookRepository.save(book)).thenReturn(book);
		
		Book book1=bookService.save(book);
		assertEquals(book1.getIsbn(),book.getIsbn());
	}
	
	void findByIdTest() throws Exception{
		BookDTO bookDTO=new BookDTO("1234567890","RestTemplate",170.14,"RamShankar", 300,"RestTemplate for advance");
		Book book= convertToBook(bookDTO);
		book.setId(2l);
		
		Optional<Book> bookOptional=Optional.of(book);
		when(bookRepository.findById(3l)).thenReturn(bookOptional);
		
		Optional<Book> book1=bookService.findById(3l);
	Book response=book1.get();
		assertEquals(response.getIsbn(),book.getIsbn());
		
	}
	
	void findBookByISBNTest() throws Exception{
		BookDTO bookDTO=new BookDTO("1234567890","RestTemplate",170.14,"RamShankar", 300,"RestTemplate for advance");
		Book book= convertToBook(bookDTO);
		book.setId(2l);
		Optional<Book> bookOptional=Optional.of(book);
		when(bookRepository.findBookByIsbn(anyString())).thenReturn(bookOptional);
		Optional<Book> book1=bookService.findBookByISBN(anyString());
		Book response=book1.get();
		assertEquals(response.getIsbn(),book.getIsbn());	
	}
	
	void findBooksByTitleTest() throws Exception{
		List<Book> booklist=new ArrayList<Book>();
		BookDTO bookDTO=new BookDTO("1234567890","RestTemplate",170.14,"RamShankar", 300,"RestTemplate for advance");
		Book book= convertToBook(bookDTO);
		book.setId(2l);
		booklist.add(book);
		
		Optional<List<Book>> bookOptional=Optional.of(booklist);
		when(bookRepository.findBooksByTitleContaining(anyString())).thenReturn(booklist);
		Optional<List<Book>> booklis= Optional.of(bookService.findBooksByTitle(anyString()));
		List<Book> response=booklis.get();
		Book book1=response.get(0);
		assertEquals(book1.getIsbn(), book.getIsbn());	
	}
//	void findAllTest() throws Exception{
//		List<Book> booklist=new ArrayList<Book>();
//		BookDTO bookDTO=new BookDTO("1234567890","RestTemplate",170.14,"RamShankar", 300,"RestTemplate for advance");
//		Book book= convertToBook(bookDTO);
//		booklist.add(book);
//		
//		when(bookRepository.findAll()).thenReturn(booklist);
//		List<Book> books=bookService.findAll();
//	    Book response=books.get(0);
//	    assertEquals(response.getIsbn(),book.getIsbn());
//	}
	
	 void deleteByIsbn() throws Exception{
		 BookDTO bookDTO=new BookDTO("1234567890","RestTemplate",170.14,"RamShankar", 300,"RestTemplate for advance");
			Book book= convertToBook(bookDTO);
			Mockito.doNothing().when(bookService).deleteByIsbn(anyString());
			Mockito.doNothing().when(bookRepository).deleteBookByIsbn(anyString());
			verify(bookService).deleteByIsbn(anyString());
	 }
	

}
