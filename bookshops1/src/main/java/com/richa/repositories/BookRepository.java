package com.richa.repositories;

import com.richa.dtos.BookDTO;
import com.richa.entities.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();

    List<Book> findBooksByTitleContaining(String title);

    List<Book> findBookByAuthor(String author);

    Optional<Book> findBookByIsbn(String isbn);
//    Book books=new Book();
//    id=books.getId();
//    Optional<Book> updateBook(BookDTO bookdto, Long id);
    
    void deleteBookByIsbn(String isbn);
}
