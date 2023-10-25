package com.richa.facades;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.richa.AppConfig;
import com.richa.dtos.BookDTO;
import com.richa.entities.Book;
import com.richa.exception.global.customexceptions.BookCreateException;
import com.richa.services.BookServiceImpl;

@SpringBootTest(classes= {BookFacadeImpl.class})
public class BookFacadeTest {
	
@Autowired
private BookFacadeImpl bookFacade;

@MockBean
private BookServiceImpl bookService;

@MockBean
private AppConfig appConfig;

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
@Test
void addBookTest() throws Exception{
	BookDTO bookDTO=new BookDTO();
	bookDTO.setISBN("1234567890");
	bookDTO.setTitle("Java");
	bookDTO.setPrice(170.14);
	bookDTO.setAuthor("Rama");
	bookDTO.setPages(300);
	bookDTO.setProvider("Java for beginners");
	bookDTO.setId(3l);
	
	Book book= convertToBook(bookDTO);
	when(bookService.count()).thenReturn(2l);
	when(appConfig.getAddbook()).thenReturn(4l);
	
	Optional<Book> bookOptional=Optional.empty();
	
	when(bookService.findBookByISBN(anyString())).thenReturn(bookOptional);
	
	when(bookService.save(any(Book.class))).thenReturn(book);
	
	BookDTO response=bookFacade.addBook(bookDTO);
	assertEquals(response.getISBN(),bookDTO.getISBN());
}

@Test
void addBookLimitExceededTest() throws Exception{
	BookDTO bookDTO=new BookDTO();
	bookDTO.setISBN("1234567890");
	bookDTO.setTitle("Java");
	bookDTO.setPrice(170.14);
	bookDTO.setAuthor("Rama");
	bookDTO.setPages(300);
	bookDTO.setProvider("Java for beginners");
	bookDTO.setId(3l);
	when(bookService.count()).thenReturn(5l);
	when(appConfig.getAddbook()).thenReturn(4l);
	
	Assertions.assertThatExceptionOfType(BookCreateException.class).isThrownBy(() -> {
		bookFacade.addBook(bookDTO);
	    }).withMessage("Book Limit Exceeded");
}

@Test
void addDuplicateBookTest() throws Exception{
	BookDTO bookDTO=new BookDTO();
	bookDTO.setISBN("1234567890");
	bookDTO.setTitle("Java");
	bookDTO.setPrice(170.14);
	bookDTO.setAuthor("Rama");
	bookDTO.setPages(300);
	bookDTO.setProvider("Java for beginners");
	bookDTO.setId(3l);
	
	Book book= convertToBook(bookDTO);
	when(bookService.count()).thenReturn(2l);
	when(appConfig.getAddbook()).thenReturn(4l);
	
	Optional<Book> bookOptional=Optional.of(book);
	
	when(bookService.findBookByISBN(anyString())).thenReturn(bookOptional);
	
	Assertions.assertThatExceptionOfType(BookCreateException.class).isThrownBy(() -> {
		bookFacade.addBook(bookDTO);
	    }).withMessage("Book already exists");
}

@Test
void updateBookTest() throws Exception{
	BookDTO bookDTO=new BookDTO("1234567890","RestTemplate",170.14,"RamShankar", 300,"RestTemplate for advance");
	Book book= convertToBook(bookDTO);
	book.setId(2l);
	Optional<Book> bookOptional=Optional.of(book);
	when(bookService.findById(2l)).thenReturn(bookOptional);
	when(bookService.save(any(Book.class))).thenReturn(book);
	
	BookDTO response=bookFacade.updateBook(bookDTO, 2l);
	assertEquals(response.getISBN(),bookDTO.getISBN());
}

@Test
void updateEmptyBookTest() throws Exception{
	BookDTO bookDTO=new BookDTO("1234567890","RestTemplate",170.14,"RamShankar", 300,"RestTemplate for advance");
	Book book= convertToBook(bookDTO);
	Optional<Book> bookOptional=Optional.empty();
	when(bookService.findById(2l)).thenReturn(bookOptional);
	
	Assertions.assertThatExceptionOfType(BookCreateException.class).isThrownBy(() -> {
		bookFacade.updateBook(bookDTO, 2l);
	    }).withMessage("Book not found");
	
}

@Test
void findBookByISBNTest() throws Exception{
	BookDTO bookDTO=new BookDTO("1234567890","RestTemplate",170.14,"RamShankar", 300,"RestTemplate for advance");
	Book book= convertToBook(bookDTO);
	Optional<Book> bookOptional=Optional.of(book);
	when(bookService.findBookByISBN(anyString())).thenReturn(bookOptional);
	
	BookDTO response=bookFacade.findBookByISBN(anyString());
	assertEquals(response.getISBN(),bookDTO.getISBN());
}

@Test
void findBookByIdTest() throws Exception{
	BookDTO bookDTO=new BookDTO("1234567890","RestTemplate",170.14,"RamShankar", 300,"RestTemplate for advance");
	Book book= convertToBook(bookDTO);
	book.setId(4l);
	Optional<Book> bookOptional=Optional.of(book);
	when(bookService.findById(4l)).thenReturn(bookOptional);
	
	BookDTO response=bookFacade.findBookById(4l);
	assertEquals(response.getISBN(),bookDTO.getISBN());
}

@Test
void findBookByTitleTest() throws Exception{
	List<Book> booklist=new ArrayList<Book>();
	BookDTO bookDTO=new BookDTO("1234567890","RestTemplate",170.14,"RamShankar", 300,"RestTemplate for advance");
	Book book= convertToBook(bookDTO);
	booklist.add(book);

	when(bookService.findBooksByTitle(anyString())).thenReturn(booklist);
	
	List<BookDTO> response=bookFacade.findBooksByTitle(anyString());
	BookDTO book1=response.get(0);
	assertEquals(book1.getISBN(),book.getIsbn());
}

//@Test
//void findAllTest() throws Exception{
//	List<Book> booklist=new ArrayList<Book>();
//	BookDTO bookDTO=new BookDTO("1234567890","RestTemplate",170.14,"RamShankar", 300,"RestTemplate for advance");
//	Book book= convertToBook(bookDTO);
//	booklist.add(book);
//
//	when(bookService.findAll()).thenReturn(booklist);
//	
//	List<BookDTO> response=bookFacade.findAll();
//	BookDTO book1=response.get(0);
//	assertEquals(book1.getISBN(),book.getIsbn());
//}

@Test
void deleteBookByIsbnTest(){
	bookFacade=Mockito.spy(bookFacade);
	BookDTO bookDTO=new BookDTO("1234567890","RestTemplate",170.14,"RamShankar", 300,"RestTemplate for advance");
	Book book= convertToBook(bookDTO);
	Mockito.doNothing().when(bookService).deleteByIsbn(anyString());
	bookFacade.deleteBookByIsbn("1233456789");
	verify(bookFacade).deleteBookByIsbn(anyString());
}
	
	
}

