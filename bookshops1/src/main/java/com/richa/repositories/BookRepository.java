package com.richa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import com.richa.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAll();

    List<Book> findBooksByTitleContaining(String title) throws Exception;

    List<Book> findBookByAuthor(String author) throws Exception;

    Optional<Book> findBookByIsbn(String isbn) throws Exception;
    
//    Book books=new Book();
//    id=books.getId();
//    Optional<Book> updateBook(BookDTO bookdto, Long id);
    
       void deleteBookByIsbn(String isbn);

}
