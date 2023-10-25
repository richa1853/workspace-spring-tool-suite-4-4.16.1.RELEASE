package com.richa.services;

import java.util.List;
import java.util.Optional;


import com.richa.entities.Book;
import com.richa.exception.global.customexceptions.BookCreateException;

public interface BookService {
	public Book save(Book book) throws Exception, BookCreateException;
    public Optional<Book> findById(Long id)throws Exception;

    public Optional<Book> findBookByISBN(String isbn)throws Exception;

    public List<Book> findBooksByAuthor(String author) throws Exception;

    public List<Book> findBooksByTitle(String title)throws Exception;
    public List<Book> findAll(Integer pageNumber, Integer pageSize) throws Exception;
    public long count()throws Exception;
    public void deleteByIsbn(String isbn);
   // public List<Book> findBooksWithSorting(String title);
}
