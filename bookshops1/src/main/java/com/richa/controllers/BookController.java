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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.richa.dtos.BookDTO;
import com.richa.exception.global.customexceptions.BookCreateException;
import com.richa.facades.BookFacade;
import com.richa.facades.BookFacadeImpl;

@RestController
@RequestMapping(value = "/books", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//The MediaType.APPLICATION_JSON_VALUE and MediaType.APPLICATION_XML_VALUE values indicate that the method can produce responses in JSON or XML format, respectively
public class BookController {

	@Autowired
	private BookFacade bookFacade;
	

	public BookController(BookFacade bookFacade) {		
		this.bookFacade = bookFacade;
	}

	@PostMapping
	public ResponseEntity<BookDTO> addBook(@RequestBody @Valid BookDTO bookDTO) throws Exception, BookCreateException {
		return new ResponseEntity<BookDTO>(bookFacade.addBook(bookDTO), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<BookDTO>> getAll(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize

			) {
		try {
			System.out.println("Inside getAll");
			return new ResponseEntity<List<BookDTO>>(bookFacade.findAll(pageNumber, pageSize), HttpStatus.OK);
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
	public void deleteBookByIsbn(@RequestParam("isbn") String isbn) {
		bookFacade.deleteBookByIsbn(isbn);
	}
	
//	@GetMapping("/{field}")
//	private List<BookDTO> getBooksWithSort(@PathVariable String title){
//		return bookFacade.findBooksWithSorting(title);
//		
//	}
}
