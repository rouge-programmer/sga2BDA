package com.library.book_management.repository;

import com.library.book_management.dto.BookAuthorDTO;
import com.library.book_management.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    // Find books by genre
    List<Book> findByGenre(String genre);
    
    // Find books by title containing
    List<Book> findByTitleContainingIgnoreCase(String title);
    
    // Custom query with INNER JOIN between Book and Author
    @Query("SELECT new com.library.book_management.dto.BookAuthorDTO(" +
           "b.id, b.title, b.isbn, b.publicationYear, b.genre, b.pageCount, " +
           "b.description, a.id, a.name, a.nationality, a.birthYear, a.email) " +
           "FROM Book b INNER JOIN b.author a")
    List<BookAuthorDTO> findAllBooksWithAuthors();
    
    // Custom query to find books by author id
    @Query("SELECT new com.library.book_management.dto.BookAuthorDTO(" +
           "b.id, b.title, b.isbn, b.publicationYear, b.genre, b.pageCount, " +
           "b.description, a.id, a.name, a.nationality, a.birthYear, a.email) " +
           "FROM Book b INNER JOIN b.author a WHERE a.id = :authorId")
    List<BookAuthorDTO> findBooksByAuthorId(Long authorId);
    
    // Find books published after a specific year
    @Query("SELECT b FROM Book b WHERE b.publicationYear > :year")
    List<Book> findBooksPublishedAfter(Integer year);
}