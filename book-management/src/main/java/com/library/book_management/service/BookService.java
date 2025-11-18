package com.library.book_management.service;

import com.library.book_management.dto.BookAuthorDTO;
import com.library.book_management.entity.Author;
import com.library.book_management.entity.Book;
import com.library.book_management.repository.AuthorRepository;
import com.library.book_management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private AuthorRepository authorRepository;
    
    // Create or save book
    public Book saveBook(Book book, Long authorId) {
        Optional<Author> author = authorRepository.findById(authorId);
        if (author.isPresent()) {
            book.setAuthor(author.get());
            return bookRepository.save(book);
        }
        throw new RuntimeException("Author not found with id: " + authorId);
    }
    
    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    // Get book by id
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    
    // Update book
    public Book updateBook(Long id, Book updatedBook, Long authorId) {
        Optional<Book> existingBook = bookRepository.findById(id);
        Optional<Author> author = authorRepository.findById(authorId);
        
        if (existingBook.isPresent() && author.isPresent()) {
            Book book = existingBook.get();
            book.setTitle(updatedBook.getTitle());
            book.setIsbn(updatedBook.getIsbn());
            book.setPublicationYear(updatedBook.getPublicationYear());
            book.setGenre(updatedBook.getGenre());
            book.setPageCount(updatedBook.getPageCount());
            book.setDescription(updatedBook.getDescription());
            book.setAuthor(author.get());
            return bookRepository.save(book);
        }
        throw new RuntimeException("Book or Author not found");
    }
    
    // Delete book
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    
    // Get all books with author information (INNER JOIN)
    public List<BookAuthorDTO> getAllBooksWithAuthors() {
        return bookRepository.findAllBooksWithAuthors();
    }
    
    // Get books by author
    public List<BookAuthorDTO> getBooksByAuthor(Long authorId) {
        return bookRepository.findBooksByAuthorId(authorId);
    }
    
    // Search books by title
    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
    
    // Get books by genre
    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }
}