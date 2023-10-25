package com.richa.facades;

import java.util.List;

import com.richa.dtos.BookDTO;
import com.richa.exception.global.customexceptions.BookCreateException;

public interface BookFacade {
	public BookDTO addBook(BookDTO bookDTO )throws Exception, BookCreateException;
	public BookDTO updateBook(BookDTO bookDTO,Long id)throws Exception, BookCreateException;
	public BookDTO findBookByISBN(String isbn) throws Exception;
	public BookDTO findBookById(long id)throws Exception;
	 public List<BookDTO> findBooksByAuthor(String author)throws Exception;
	 public List<BookDTO> findBooksByTitle(String str) throws Exception;
	 public List<BookDTO> findAll(Integer pageNumber, Integer pageSize) throws Exception;
	 public void deleteBookByIsbn(String isbn);
	// public List<BookDTO> findBooksWithSorting(String title);

}
