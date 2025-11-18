package com.library.book_management.repository;

import com.library.book_management.dto.BookAuthorDTO;
import com.library.book_management.entity.Author;
import com.library.book_management.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private BookRepository bookRepository;
    
    private Author testAuthor;
    private Book testBook;
    
    @BeforeEach
    public void setUp() {
        testAuthor = Author.builder()
            .name("Test Author")
            .nationality("American")
            .birthYear(1980)
            .email("test@author.com")
            .build();
        
        entityManager.persist(testAuthor);
        
        testBook = Book.builder()
            .title("Test Book")
            .isbn("978-1234567890")
            .publicationYear(2020)
            .genre("Fiction")
            .pageCount(300)
            .description("A test book")
            .author(testAuthor)
            .build();
        
        entityManager.persist(testBook);
        entityManager.flush();
    }
    
    @Test
    public void testFindByGenre() {
        List<Book> books = bookRepository.findByGenre("Fiction");
        
        assertThat(books).isNotEmpty();
        assertThat(books.get(0).getGenre()).isEqualTo("Fiction");
    }
    
    @Test
    public void testFindByTitleContaining() {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase("test");
        
        assertThat(books).isNotEmpty();
        assertThat(books.get(0).getTitle()).contains("Test");
    }
    
    @Test
    public void testFindAllBooksWithAuthors() {
        List<BookAuthorDTO> booksWithAuthors = bookRepository.findAllBooksWithAuthors();
        
        assertThat(booksWithAuthors).isNotEmpty();
        BookAuthorDTO dto = booksWithAuthors.get(0);
        assertThat(dto.getBookTitle()).isNotNull();
        assertThat(dto.getAuthorName()).isNotNull();
    }
    
    @Test
    public void testFindBooksByAuthorId() {
        List<BookAuthorDTO> books = bookRepository.findBooksByAuthorId(testAuthor.getId());
        
        assertThat(books).isNotEmpty();
        assertThat(books.get(0).getAuthorId()).isEqualTo(testAuthor.getId());
    }
    
    @Test
    public void testFindBooksPublishedAfter() {
        List<Book> books = bookRepository.findBooksPublishedAfter(2015);
        
        assertThat(books).isNotEmpty();
        assertThat(books.get(0).getPublicationYear()).isGreaterThan(2015);
    }
    
    @Test
    public void testSaveBook() {
        Book newBook = Book.builder()
            .title("New Book")
            .isbn("978-9876543210")
            .publicationYear(2023)
            .genre("Mystery")
            .pageCount(250)
            .author(testAuthor)
            .build();
        
        Book saved = bookRepository.save(newBook);
        
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo("New Book");
    }
    
    @Test
    public void testUpdateBook() {
        testBook.setTitle("Updated Book");
        Book updated = bookRepository.save(testBook);
        
        assertThat(updated.getTitle()).isEqualTo("Updated Book");
    }
    
    @Test
    public void testDeleteBook() {
        Long id = testBook.getId();
        bookRepository.deleteById(id);
        
        assertThat(bookRepository.findById(id)).isEmpty();
    }
}