package com.library.book_management.repository;

import com.library.book_management.entity.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AuthorRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private AuthorRepository authorRepository;
    
    private Author testAuthor;
    
    @BeforeEach
    public void setUp() {
        testAuthor = Author.builder()
            .name("Test Author")
            .nationality("American")
            .birthYear(1980)
            .email("test@author.com")
            .biography("Test biography")
            .build();
        
        entityManager.persist(testAuthor);
        entityManager.flush();
    }
    
    @Test
    public void testFindByNationality() {
        List<Author> authors = authorRepository.findByNationality("American");
        
        assertThat(authors).isNotEmpty();
        assertThat(authors.get(0).getNationality()).isEqualTo("American");
    }
    
    @Test
    public void testFindByNameContaining() {
        List<Author> authors = authorRepository.findByNameContainingIgnoreCase("test");
        
        assertThat(authors).isNotEmpty();
        assertThat(authors.get(0).getName()).contains("Test");
    }
    
    @Test
    public void testFindAuthorsBornAfter() {
        List<Author> authors = authorRepository.findAuthorsBornAfter(1970);
        
        assertThat(authors).isNotEmpty();
        assertThat(authors.get(0).getBirthYear()).isGreaterThan(1970);
    }
    
    @Test
    public void testSaveAuthor() {
        Author newAuthor = Author.builder()
            .name("New Author")
            .nationality("British")
            .birthYear(1990)
            .email("new@author.com")
            .build();
        
        Author saved = authorRepository.save(newAuthor);
        
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("New Author");
    }
    
    @Test
    public void testFindById() {
        Author found = authorRepository.findById(testAuthor.getId()).orElse(null);
        
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Test Author");
    }
    
    @Test
    public void testUpdateAuthor() {
        testAuthor.setName("Updated Author");
        Author updated = authorRepository.save(testAuthor);
        
        assertThat(updated.getName()).isEqualTo("Updated Author");
    }
    
    @Test
    public void testDeleteAuthor() {
        Long id = testAuthor.getId();
        authorRepository.deleteById(id);
        
        assertThat(authorRepository.findById(id)).isEmpty();
    }
}