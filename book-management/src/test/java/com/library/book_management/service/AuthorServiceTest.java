package com.library.book_management.service;

import com.library.book_management.entity.Author;
import com.library.book_management.repository.AuthorRepository;
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
public class AuthorServiceTest {
    
    @Mock
    private AuthorRepository authorRepository;
    
    @InjectMocks
    private AuthorService authorService;
    
    private Author testAuthor;
    
    @BeforeEach
    public void setUp() {
        testAuthor = Author.builder()
            .id(1L)
            .name("Test Author")
            .nationality("American")
            .birthYear(1980)
            .email("test@author.com")
            .biography("Test biography")
            .build();
    }
    
    @Test
    public void testSaveAuthor() {
        when(authorRepository.save(any(Author.class))).thenReturn(testAuthor);
        
        Author saved = authorService.saveAuthor(testAuthor);
        
        assertThat(saved).isNotNull();
        assertThat(saved.getName()).isEqualTo("Test Author");
        verify(authorRepository, times(1)).save(testAuthor);
    }
    
    @Test
    public void testGetAllAuthors() {
        List<Author> authors = Arrays.asList(testAuthor);
        when(authorRepository.findAll()).thenReturn(authors);
        
        List<Author> result = authorService.getAllAuthors();
        
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Test Author");
        verify(authorRepository, times(1)).findAll();
    }
    
    @Test
    public void testGetAuthorById() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(testAuthor));
        
        Optional<Author> found = authorService.getAuthorById(1L);
        
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Test Author");
        verify(authorRepository, times(1)).findById(1L);
    }
    
    @Test
    public void testUpdateAuthor_Success() {
        Author updatedAuthor = Author.builder()
            .name("Updated Author")
            .nationality("British")
            .birthYear(1985)
            .email("updated@author.com")
            .biography("Updated biography")
            .build();
        
        when(authorRepository.findById(1L)).thenReturn(Optional.of(testAuthor));
        when(authorRepository.save(any(Author.class))).thenReturn(testAuthor);
        
        Author result = authorService.updateAuthor(1L, updatedAuthor);
        
        assertThat(result).isNotNull();
        verify(authorRepository, times(1)).findById(1L);
        verify(authorRepository, times(1)).save(any(Author.class));
    }
    
    @Test
    public void testUpdateAuthor_NotFound() {
        when(authorRepository.findById(anyLong())).thenReturn(Optional.empty());
        
        Author updatedAuthor = Author.builder().name("Updated").build();
        
        assertThatThrownBy(() -> authorService.updateAuthor(999L, updatedAuthor))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Author not found");
    }
    
    @Test
    public void testDeleteAuthor() {
        doNothing().when(authorRepository).deleteById(1L);
        
        authorService.deleteAuthor(1L);
        
        verify(authorRepository, times(1)).deleteById(1L);
    }
    
    @Test
    public void testGetAuthorsByNationality() {
        List<Author> authors = Arrays.asList(testAuthor);
        when(authorRepository.findByNationality("American")).thenReturn(authors);
        
        List<Author> result = authorService.getAuthorsByNationality("American");
        
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNationality()).isEqualTo("American");
    }
    
    @Test
    public void testSearchAuthorsByName() {
        List<Author> authors = Arrays.asList(testAuthor);
        when(authorRepository.findByNameContainingIgnoreCase("test")).thenReturn(authors);
        
        List<Author> result = authorService.searchAuthorsByName("test");
        
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).contains("Test");
    }
}