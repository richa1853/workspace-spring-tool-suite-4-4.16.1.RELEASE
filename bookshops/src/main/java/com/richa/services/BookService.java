//package main.java.com.richa.services;
//
//import com.richa.entities.Book;
//import com.richa.repositories.BookRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class BookService {
//
//    private BookRepository bookRepository;
//
//    @Autowired
//    public BookService(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }
//
//    public Book save(Book book){
//        return bookRepository.save(book);
//    }
//
//    public Optional<Book> findBookByISBN(String isbn){
//        return bookRepository.findBookByIsbn(isbn);
//    }
//
//    @Transactional
//    public List<Book> findBooksByAuthor(String author){
//        return bookRepository.findBookByAuthor(author);
//    }
//
//    public List<Book> findBooksByTitle(String title){
//        return bookRepository.findBooksByTitleContaining(title);
//    }
//
//    public List<Book> findAll() {
//        return bookRepository.findAll();
//    }
//}