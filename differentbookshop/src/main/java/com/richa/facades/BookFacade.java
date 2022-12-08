//package com.richa.facades;
//
//import com.first.springbootApp.exception.global.customexceptions.CustomerCreateException;
//import com.richa.AppConfig;
//import com.richa.constants.ErrorCode;
//import com.richa.dtos.BookDTO;
//import com.richa.entities.Book;
//import com.richa.globalCustomExceptions.BookCreateException;
//import com.richa.services.BookService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import lombok.Builder;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import static com.richa.constants.ErrorCode.BOOK_ALREADY_EXISTS;
//import static com.richa.constants.ErrorCode.BOOK_NOT_FOUND;
//import static com.richa.constants.ErrorCode.BOOKS_EXCEEDED;
//
////@Service
//@Component
//public class BookFacade {
//
//	@Autowired
//    private BookService bookService;
//    @Autowired
//    private AppConfig appConfig;
//
//	public BookDTO addBook(BookDTO bookDTO ){
//		// count of books return
//		BookDTO bookDTO1 = new BookDTO();
//		long current_books = bookService.count();
//		long bookAddLimit = appConfig.getAddbook();
//		if (current_books > bookAddLimit) {
//			//customexception
////			String errorMsg="Cannot create more than"+bookAddLimit+"Books.current size of books"+current_books;
////			throw new BookCreateException(errorMsg, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), bookDTO.getTitle());
//			bookDTO1.setErrorMessage(BOOKS_EXCEEDED.getMessage());			bookDTO1.setError(BOOKS_EXCEEDED.getCode());
//			return bookDTO1;
//		} else {
//			Optional<Book> bookOptional = bookService.findBookByISBN(bookDTO.getISBN());
//			if (bookOptional.isPresent()) {
//				bookDTO1.setErrorMessage(BOOK_ALREADY_EXISTS.getMessage());
//				bookDTO1.setError(BOOK_ALREADY_EXISTS.getCode());
//				return bookDTO1;
//			} else {
//				Book book = new Book();
//				book.setIsbn(bookDTO.getISBN());
//				book.setTitle(bookDTO.getTitle());
//				book.setPrice(bookDTO.getPrice());
//				book.setAuthor(bookDTO.getAuthor());
//				book.setPages(bookDTO.getPages());
//				book.setProvider(bookDTO.getProvider());
//
//				System.out.println(book);
//				book = bookService.save(book);
//				bookDTO.setId(book.getId());
//				bookDTO1 = bookDTO;
//				return bookDTO;
//			}
//		}
//	}
//
//    public BookDTO updateBook(BookDTO bookDTO,Long id) {
//    	Optional<Book> bookOptional=bookService.findById(id);
//    	System.out.println("id is"+ bookDTO.getId());
//    	if(bookOptional.isPresent()) {
//    	Book book=new Book();
//    	book.setId(id);
//    	book.setIsbn(bookDTO.getISBN());
//    	book.setTitle(bookDTO.getTitle());
//    	book.setPrice(bookDTO.getPrice());
//    	book.setAuthor(bookDTO.getAuthor());
//    	book.setPages(bookDTO.getPages());
//    	book.setProvider(bookDTO.getProvider());
//    	
//    	System.out.println(book);
//    	
//        book = bookService.save(book);
//    	return bookDTO;
//    }
//    	else {
//    		bookDTO.setErrorMessage(BOOK_NOT_FOUND.getMessage());
//            bookDTO.setError(BOOK_NOT_FOUND.getCode());
//            return bookDTO;
//    	}
//    }
//
//    public BookDTO findBookByISBN(String isbn){
//        Optional<Book> bookOptional = bookService.findBookByISBN(isbn);
//        if(bookOptional.isPresent()) {
//            return BookDTO.fromBook(bookOptional.get());
//        }
//
//        BookDTO bookDTO = new BookDTO();
//        bookDTO.setErrorMessage(BOOK_NOT_FOUND.getMessage());
//        bookDTO.setError(BOOK_NOT_FOUND.getCode());
//        return bookDTO;
//    }
//
//    public List<BookDTO> findBooksByAuthor(String author){
//        List<Book> books = bookService.findBooksByAuthor(author);
//        if(!books.isEmpty()) {
//            return books.stream().map(BookDTO::fromBook).collect(Collectors.toList());
//        }
//        return new ArrayList<>(0);
//    }
//    public List<BookDTO> findBooksByNameSearch(String str){
//        List<Book> books = bookService.findBooksByTitle(str);
//        if(!books.isEmpty()) {
//            return books.stream().map(BookDTO::fromBook).collect(Collectors.toList());
//        }
//        return new ArrayList<>(0);
//    }
//
//    public List<BookDTO> findAll(){
//        return bookService.findAll()
//                .stream()
//                .map(BookDTO::fromBook)
//                .collect(Collectors.toList());
//    }
//    
//    public void deleteBookByIsbn(String isbn) {
//    	bookService.deleteByIsbn(isbn);
//    }
//}
