package com.library.book_management.repository;

import com.library.book_management.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    // Find authors by nationality
    List<Author> findByNationality(String nationality);
    
    // Find authors by name containing
    List<Author> findByNameContainingIgnoreCase(String name);
    
    // Find authors born after a specific year
    @Query("SELECT a FROM Author a WHERE a.birthYear > :year")
    List<Author> findAuthorsBornAfter(Integer year);
}