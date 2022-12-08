package com.richa.services;

import com.richa.dtos.BookDTO;
import com.richa.entities.Book;
import com.richa.exception.global.customexceptions.BookCreateException;
import com.richa.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book save(Book book) throws Exception, BookCreateException{
    	Book book1=bookRepository.save(book);
    	
    	if(book1!=null) {
    	  
    		System.out.println("Book id is" + book1.getId());
    	}
    	
        return book1;
    }

    public Optional<Book> findById(Long id)throws Exception {
    	return bookRepository.findById(id);
    	
    }

    public Optional<Book> findBookByISBN(String isbn)throws Exception{
        return bookRepository.findBookByIsbn(isbn);
    }

    @Transactional
    public List<Book> findBooksByAuthor(String author) throws Exception{
        return bookRepository.findBookByAuthor(author);
    }

    public List<Book> findBooksByTitle(String title)throws Exception{
        return bookRepository.findBooksByTitleContaining(title);
    }

    public List<Book> findAll() throws Exception {
        return bookRepository.findAll();
    }
    public long count()throws Exception {
    	return bookRepository.count();
    }
    @Transactional
    public void deleteByIsbn(String isbn) {
      bookRepository.deleteBookByIsbn(isbn);
    }
}