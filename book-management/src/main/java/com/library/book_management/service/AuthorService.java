package com.library.book_management.service;

import com.library.book_management.entity.Author;
import com.library.book_management.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorService {
    
    @Autowired
    private AuthorRepository authorRepository;
    
    // Create or update author
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }
    
    // Get all authors
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
    
    // Get author by id
    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }
    
    // Update author
    public Author updateAuthor(Long id, Author updatedAuthor) {
        Optional<Author> existingAuthor = authorRepository.findById(id);
        if (existingAuthor.isPresent()) {
            Author author = existingAuthor.get();
            author.setName(updatedAuthor.getName());
            author.setNationality(updatedAuthor.getNationality());
            author.setBirthYear(updatedAuthor.getBirthYear());
            author.setEmail(updatedAuthor.getEmail());
            author.setBiography(updatedAuthor.getBiography());
            return authorRepository.save(author);
        }
        throw new RuntimeException("Author not found with id: " + id);
    }
    
    // Delete author
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
    
    // Find authors by nationality
    public List<Author> getAuthorsByNationality(String nationality) {
        return authorRepository.findByNationality(nationality);
    }
    
    // Search authors by name
    public List<Author> searchAuthorsByName(String name) {
        return authorRepository.findByNameContainingIgnoreCase(name);
    }
}