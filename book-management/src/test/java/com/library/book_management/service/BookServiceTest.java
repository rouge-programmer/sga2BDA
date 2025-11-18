package com.library.book_management.service;

import com.library.book_management.dto.BookAuthorDTO;
import com.library.book_management.entity.Author;
import com.library.book_management.entity.Book;
import com.library.book_management.repository.AuthorRepository;
import com.library.book_management.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    
    @Mock
    private BookRepository bookRepository;
    
    @Mock
    private AuthorRepository authorRepository;
    
    @InjectMocks
    private BookService bookService;
    
    private Author testAuthor;
    private Book testBook;
    
    @BeforeEach
    public void setUp() {
        testAuthor = Author.builder()
            .id(1L)
            .name("Test Author")
            .nationality("American")
            .birthYear(1980)
            .email("test@author.com")
            .build();
        
        testBook = Book.builder()
            .id(1L)
            .title("Test Book")
            .isbn("978-1234567890")
            .publicationYear(2020)
            .genre("Fiction")
            .pageCount(300)
            .description("A test book")
            .author(testAuthor)
            .build();
    }
    
    @Test
    public void testSaveBook_Success() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(testAuthor));
        when(bookRepository.save(any(Book.class))).thenReturn(testBook);
        
        Book saved = bookService.saveBook(testBook, 1L);
        
        assertThat(saved).isNotNull();
        assertThat(saved.getTitle()).isEqualTo("Test Book");
        verify(authorRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(any(Book.class));
    }
    
    @Test
    public void testSaveBook_AuthorNotFound() {
        when(authorRepository.findById(anyLong())).thenReturn(Optional.empty());
        
        assertThatThrownBy(() -> bookService.saveBook(testBook, 999L))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Author not found");
    }
    
    @Test
    public void testGetAllBooks() {
        List<Book> books = Arrays.asList(testBook);
        when(bookRepository.findAll()).thenReturn(books);
        
        List<Book> result = bookService.getAllBooks();
        
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Test Book");
        verify(bookRepository, times(1)).findAll();
    }
    
    @Test
    public void testGetBookById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));
        
        Optional<Book> found = bookService.getBookById(1L);
        
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Test Book");
        verify(bookRepository, times(1)).findById(1L);
    }
    
    @Test
    public void testUpdateBook_Success() {
        Book updatedBook = Book.builder()
            .title("Updated Book")
            .isbn("978-9876543210")
            .publicationYear(2023)
            .genre("Mystery")
            .pageCount(250)
            .build();
        
        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));
        when(authorRepository.findById(1L)).thenReturn(Optional.of(testAuthor));
        when(bookRepository.save(any(Book.class))).thenReturn(testBook);
        
        Book result = bookService.updateBook(1L, updatedBook, 1L);
        
        assertThat(result).isNotNull();
        verify(bookRepository, times(1)).findById(1L);
        verify(authorRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(any(Book.class));
    }
    
    @Test
    public void testUpdateBook_BookNotFound() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());
        
        Book updatedBook = Book.builder().title("Updated").build();
        
        assertThatThrownBy(() -> bookService.updateBook(999L, updatedBook, 1L))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Book or Author not found");
    }
    
    @Test
    public void testDeleteBook() {
        doNothing().when(bookRepository).deleteById(1L);
        
        bookService.deleteBook(1L);
        
        verify(bookRepository, times(1)).deleteById(1L);
    }
    
    @Test
    public void testGetAllBooksWithAuthors() {
        BookAuthorDTO dto = BookAuthorDTO.builder()
            .bookId(1L)
            .bookTitle("Test Book")
            .authorId(1L)
            .authorName("Test Author")
            .build();
        
        List<BookAuthorDTO> dtos = Arrays.asList(dto);
        when(bookRepository.findAllBooksWithAuthors()).thenReturn(dtos);
        
        List<BookAuthorDTO> result = bookService.getAllBooksWithAuthors();
        
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getBookTitle()).isEqualTo("Test Book");
        assertThat(result.get(0).getAuthorName()).isEqualTo("Test Author");
    }
    
    @Test
    public void testSearchBooksByTitle() {
        List<Book> books = Arrays.asList(testBook);
        when(bookRepository.findByTitleContainingIgnoreCase("test")).thenReturn(books);
        
        List<Book> result = bookService.searchBooksByTitle("test");
        
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).contains("Test");
    }
    
    @Test
    public void testGetBooksByGenre() {
        List<Book> books = Arrays.asList(testBook);
        when(bookRepository.findByGenre("Fiction")).thenReturn(books);
        
        List<Book> result = bookService.getBooksByGenre("Fiction");
        
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getGenre()).isEqualTo("Fiction");
    }
}