package com.richa.services;

import com.richa.dtos.BookDTO;
import com.richa.entities.Book;
import com.richa.exception.global.customexceptions.BookCreateException;
import com.richa.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) throws Exception, BookCreateException{
    	Book book1=bookRepository.save(book);
//    	
//    	if(book1!=null) {
//    	  
//    		System.out.println("Book id is" + book1.getId());
//    	}
//    	
        return book1;
    }

    @Override
    public Optional<Book> findById(Long id)throws Exception {
    	return bookRepository.findById(id);
    	
    }
    @Override
    public Optional<Book> findBookByISBN(String isbn)throws Exception{
        return bookRepository.findBookByIsbn(isbn);
    }

    @Transactional
    @Override
    public List<Book> findBooksByAuthor(String author) throws Exception{
        return bookRepository.findBookByAuthor(author);
    }
    @Override
    public List<Book> findBooksByTitle(String title)throws Exception{
        return bookRepository.findBooksByTitleContaining(title);
    }
    @Override
    public List<Book> findAll(Integer pageNumber, Integer pageSize) throws Exception {
    	Pageable p = PageRequest.of(pageNumber, pageSize);
    	Page<Book> pageBook = this.bookRepository.findAll(p);
    	List<Book> allBooks = pageBook.getContent();
        return allBooks;
    }
    @Override
    public long count()throws Exception {
    	return bookRepository.count();
    }
    @Transactional
    @Override
    public void deleteByIsbn(String isbn) {
      bookRepository.deleteBookByIsbn(isbn);
    }
//    @Override
//    public List<Book> findBooksWithSorting(String title){
//    	return bookRepository.findAll(Sort);
//    }
}