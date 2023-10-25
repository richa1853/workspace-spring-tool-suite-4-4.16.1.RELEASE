package com.richa.facades;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.richa.AppConfig;
import com.richa.dtos.BookDTO;
import com.richa.entities.Book;
import com.richa.exception.global.customexceptions.BookCreateException;
import com.richa.services.BookService;

@Component
public class BookFacadeImpl implements BookFacade{

	@Autowired
    private BookService bookService;
	
    @Autowired
    private AppConfig appConfig;
    
    Log log=LogFactory.getLog(BookFacadeImpl.class);
    
 	public BookFacadeImpl(BookService bookService, AppConfig appConfig) {
		this.bookService = bookService;
		this.appConfig = appConfig;
	}
    @Override
	public BookDTO addBook(BookDTO bookDTO )throws Exception, BookCreateException
	{
		// count of books return
    	log.info("Book"+bookDTO);
    	log.info("INFO book");
   	    log.trace("Trace book");
   	    log.debug("debug book");
   	    log.warn("Warn book");
   	    log.error("Error book");
//   	log.Fatal("Fatal book");
		BookDTO bookDTO1 = new BookDTO();
		long current_books = bookService.count();
//		System.out.println("bookCount"+current_books);
		long bookAddLimit = appConfig.getAddbook();
//		System.out.println("book limit is"+bookAddLimit);
		if (current_books > bookAddLimit) {
//			String errorMsg = "Cannot create more than "+bookAddLimit+" books";
			String errorMsg = "Book Limit Exceeded";
			log.error(errorMsg);
			throw new BookCreateException(errorMsg, HttpStatus.BAD_REQUEST.value());
		} else { 
			Optional<Book> bookOptional = bookService.findBookByISBN(bookDTO.getISBN());
			if (bookOptional.isPresent()) {
//				System.out.println("book exists can't add");
				String errorMsg ="Book already exists";
				log.trace(errorMsg);
//				System.out.println(errorMsg);
				throw new BookCreateException(errorMsg, HttpStatus.BAD_REQUEST.value());
			} else {
				log.info("Going to add book");
//				System.out.println("Going to add book");
				Book book = new Book();
				book.setIsbn(bookDTO.getISBN());
				book.setTitle(bookDTO.getTitle());
				book.setPrice(bookDTO.getPrice());
				book.setAuthor(bookDTO.getAuthor());
				book.setPages(bookDTO.getPages());
				book.setProvider(bookDTO.getProvider());

//				System.out.println(book);
//				log.info(" after adding books: "+book);
				book = bookService.save(book);
//				System.out.println("Book Entity response");
//				System.out.println(book);
				bookDTO.setId(book.getId());
				bookDTO1 = bookDTO;
//				System.out.println("BookDTO printing");
//				System.out.println(bookDTO);
				log.info("Added book is "+bookDTO);
				return bookDTO;
			}
		}
	}
    @Override
    public BookDTO updateBook(BookDTO bookDTO,Long id)throws Exception, BookCreateException {
    	Optional<Book> bookOptional=bookService.findById(id);
//    	System.out.println("id is"+ bookDTO.getId());
    	log.info("Current book is"+bookDTO);
    	if(bookOptional.isPresent()) {
    	Book book=new Book();
    	book.setId(id);
    	book.setIsbn(bookDTO.getISBN());
    	book.setTitle(bookDTO.getTitle());
    	book.setPrice(bookDTO.getPrice());
    	book.setAuthor(bookDTO.getAuthor());
    	book.setPages(bookDTO.getPages());
    	book.setProvider(bookDTO.getProvider());
    	
//    	System.out.println(book);
    	
        book = bookService.save(book);
        log.info("updated book is "+book);
    	return bookDTO;
    }
    	else {
    		String errorMsg ="Book not found";
    		log.warn(errorMsg);
			throw new BookCreateException(errorMsg, HttpStatus.BAD_REQUEST.value());
    	}
    }
    @Override
    public BookDTO findBookByISBN(String isbn) throws Exception{
        Optional<Book> bookOptional = bookService.findBookByISBN(isbn);
//        System.out.println("isbn found");
       
        if(bookOptional.isPresent()) {
            return BookDTO.fromBook(bookOptional.get());
        }
        else {
        String errorMsg ="Book is not there";
        log.error(errorMsg);
        throw new Exception(errorMsg);
    }
    }
    @Override
    public BookDTO findBookById(long id)throws Exception{
    	Optional<Book> bookOptional = bookService.findById(id);
        if(bookOptional.isPresent()) {
            return BookDTO.fromBook(bookOptional.get());
        }
        else {
        	String err="Book not found with the id ";
        	log.error(err);
        	throw new Exception(err);
        }
    }
    @Override
    public List<BookDTO> findBooksByAuthor(String author)throws Exception{
        List<Book> books = bookService.findBooksByAuthor(author);
//        System.out.println("Author found");
        if(!books.isEmpty()) {
            return books.stream().map(BookDTO::fromBook).collect(Collectors.toList());
        }
        else {
        String errorMsg ="Book is not found";
        log.error(errorMsg);
        throw new Exception(errorMsg);
    }
    }
    @Override
    public List<BookDTO> findBooksByTitle(String str) throws Exception{
        List<Book> books = bookService.findBooksByTitle(str);
        if(!books.isEmpty()) {
            return books.stream().map(BookDTO::fromBook).collect(Collectors.toList());
        }
        String errorMsg ="Book not found"; 
        throw new Exception(errorMsg);
    }
    @Override
    public List<BookDTO> findAll(Integer pageNumber, Integer pageSize) throws Exception{
        log.info("fetching all book details");
    	return bookService.findAll(pageNumber, pageSize)
                .stream()
                .map(BookDTO::fromBook)
                .collect(Collectors.toList());
    }
    @Override
    public void deleteBookByIsbn(String isbn) {
    	bookService.deleteByIsbn(isbn);
//    	System.out.println("Book Deleted");
    	log.info("book deleted of isbn: "+isbn);
    }
//    @Override
//    public List<BookDTO> findBooksWithSorting(String title){
//    	List<Book> books = bookService.findBooksWithSorting(title);
//    	return books.stream().map(BookDTO::fromBook).collect(Collectors.toList()); 
//    }
}
