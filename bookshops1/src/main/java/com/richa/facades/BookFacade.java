package com.richa.facades;

import com.richa.AppConfig;
import com.richa.dtos.BookDTO;
import com.richa.entities.Book;
import com.richa.exception.global.customexceptions.BookCreateException;
import com.richa.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



//@Service
@Component
public class BookFacade {

	@Autowired
    private BookService bookService;
	
    @Autowired
    private AppConfig appConfig;

	public BookDTO addBook(BookDTO bookDTO )throws Exception, BookCreateException
	{
		// count of books return
		BookDTO bookDTO1 = new BookDTO();
		long current_books = bookService.count();
		long bookAddLimit = appConfig.getAddbook();
		if (current_books > bookAddLimit) {
			String errorMsg = "Cannot create more than "+bookAddLimit+" books";
			throw new BookCreateException(errorMsg, HttpStatus.BAD_REQUEST.value());
		} else { 
			Optional<Book> bookOptional = bookService.findBookByISBN(bookDTO.getISBN());
			if (bookOptional.isPresent()) {
				String errorMsg ="Book already exists";
				throw new BookCreateException(errorMsg, HttpStatus.BAD_REQUEST.value());
			} else {
				Book book = new Book();
				book.setIsbn(bookDTO.getISBN());
				book.setTitle(bookDTO.getTitle());
				book.setPrice(bookDTO.getPrice());
				book.setAuthor(bookDTO.getAuthor());
				book.setPages(bookDTO.getPages());
				book.setProvider(bookDTO.getProvider());

				System.out.println(book);
				book = bookService.save(book);
				bookDTO.setId(book.getId());
				bookDTO1 = bookDTO;
				return bookDTO;
			}
		}
	}

    public BookDTO updateBook(BookDTO bookDTO,Long id)throws Exception, BookCreateException {
    	Optional<Book> bookOptional=bookService.findById(id);
    	System.out.println("id is"+ bookDTO.getId());
    	if(bookOptional.isPresent()) {
    	Book book=new Book();
    	book.setId(id);
    	book.setIsbn(bookDTO.getISBN());
    	book.setTitle(bookDTO.getTitle());
    	book.setPrice(bookDTO.getPrice());
    	book.setAuthor(bookDTO.getAuthor());
    	book.setPages(bookDTO.getPages());
    	book.setProvider(bookDTO.getProvider());
    	
    	System.out.println(book);
    	
        book = bookService.save(book);
    	return bookDTO;
    }
    	else {
    		String errorMsg ="Book not found";
			throw new BookCreateException(errorMsg, HttpStatus.BAD_REQUEST.value());
    	}
    }

    public BookDTO findBookByISBN(String isbn) throws Exception{
        Optional<Book> bookOptional = bookService.findBookByISBN(isbn);
        if(bookOptional.isPresent()) {
            return BookDTO.fromBook(bookOptional.get());
        }
        else {
        String errorMsg ="Book is not there";
        throw new Exception(errorMsg);
    }
    }
    
    public BookDTO findBookById(long id)throws Exception{
    	Optional<Book> bookOptional = bookService.findById(id);
        if(bookOptional.isPresent()) {
            return BookDTO.fromBook(bookOptional.get());
        }
        else throw new Exception("Book not found with the id ");
    }
    public List<BookDTO> findBooksByAuthor(String author)throws Exception{
        List<Book> books = bookService.findBooksByAuthor(author);
        if(!books.isEmpty()) {
            return books.stream().map(BookDTO::fromBook).collect(Collectors.toList());
        }
        else {
        String errorMsg ="Book is not found";
        throw new Exception(errorMsg);
    }
    }
    public List<BookDTO> findBooksByTitle(String str) throws Exception{
        List<Book> books = bookService.findBooksByTitle(str);
        if(!books.isEmpty()) {
            return books.stream().map(BookDTO::fromBook).collect(Collectors.toList());
        }
        String errorMsg ="Book not found";
        throw new Exception(errorMsg);
    }

    public List<BookDTO> findAll() throws Exception{
        return bookService.findAll()
                .stream()
                .map(BookDTO::fromBook)
                .collect(Collectors.toList());
    }
    
    public void deleteBookByIsbn(String isbn) {
    	bookService.deleteByIsbn(isbn);
    }
}
