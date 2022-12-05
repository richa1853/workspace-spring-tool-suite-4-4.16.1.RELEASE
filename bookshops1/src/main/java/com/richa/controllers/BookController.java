package com.richa.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.richa.dtos.BookDTO;
import com.richa.facades.BookFacade;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookFacade bookFacade;

    @PostMapping
    public BookDTO addBook(@RequestBody @Valid BookDTO bookDTO) {
        return bookFacade.addBook(bookDTO);
    }

    @GetMapping
    public List<BookDTO> getAll(){
    	System.out.println("Inside getAll");

        return bookFacade.findAll();
    }

    @GetMapping(params = "isbn")
    public BookDTO getBookByISBN(@RequestParam("isbn") String isbn) {
        return bookFacade.findBookByISBN(isbn);
    }
    @GetMapping(params="id")
    public BookDTO getBookById(@RequestParam("id") long id) {
    	return bookFacade.findBookById(id);
    }

    @GetMapping(params = "author")
    public List<BookDTO> getBooksByAuthor(@RequestParam("author") String author) {
        return bookFacade.findBooksByAuthor(author);
    }

    @GetMapping(params = "title")
    public List<BookDTO> getBooksByTitle(@RequestParam("title") String title) {
        return bookFacade.findBooksByNameSearch(title);
    }
    /*  Book book = Book.builder().isbn("ISBN00001").title("El niño del pijama").price(9.99).author("Author Name").pages(200).provider("provider").build();
        bookFacade.addBook(BookDTO.fromBook(book));*/
    @PutMapping
    public BookDTO updateBook(@RequestBody @Valid BookDTO bookDTO) {
    	return bookFacade.updateBook(bookDTO, bookDTO.getId());
    }
    
    
    @DeleteMapping(params="isbn")
    public void deleteBookById(@RequestParam("isbn") String isbn) {
           bookFacade.deleteBookByIsbn(isbn);    	
    }
}
