package com.richa.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.richa.dtos.BookDTO;
import com.richa.exception.global.customexceptions.BookCreateException;
import com.richa.facades.BookFacade;

@RestController
@RequestMapping(value = "/books", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class BookController {

	@Autowired
	private BookFacade bookFacade;

	@PostMapping
	public ResponseEntity<BookDTO> addBook(@RequestBody @Valid BookDTO bookDTO) throws Exception, BookCreateException {
		return new ResponseEntity<BookDTO>(bookFacade.addBook(bookDTO), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<BookDTO>> getAll() {
		try {
			System.out.println("Inside getAll");
			return new ResponseEntity<List<BookDTO>>(bookFacade.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(params = "isbn")
	public ResponseEntity<BookDTO> getBookByISBN(@RequestParam("isbn") String isbn) throws Exception {
		return new ResponseEntity<BookDTO>(bookFacade.findBookByISBN(isbn), HttpStatus.OK);
	}

	@GetMapping(params = "id")
	public ResponseEntity<BookDTO> getBookById(@RequestParam("id") long id) throws Exception {
		return new ResponseEntity<BookDTO>(bookFacade.findBookById(id), HttpStatus.OK);
	}

	@GetMapping(params = "author")
	public ResponseEntity<List<BookDTO>> getBooksByAuthor(@RequestParam("author") String author) throws Exception {
		return new ResponseEntity<List<BookDTO>>(bookFacade.findBooksByAuthor(author), HttpStatus.OK);
	}

	@GetMapping("{title}")
	public ResponseEntity<List<BookDTO>> getBooksByTitle(@PathParam("title") String title) throws Exception {
		return new ResponseEntity<List<BookDTO>>(bookFacade.findBooksByTitle(title), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<BookDTO> updateBook(@RequestBody @Valid BookDTO bookDTO) throws Exception {
		return new ResponseEntity<BookDTO>(bookFacade.updateBook(bookDTO, bookDTO.getId()), HttpStatus.OK);
	}

	@DeleteMapping(params = "isbn")
	public void deleteBookById(@RequestParam("isbn") String isbn) {
		bookFacade.deleteBookByIsbn(isbn);
	}
}
