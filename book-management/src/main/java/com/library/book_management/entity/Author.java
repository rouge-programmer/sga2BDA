package com.library.book_management.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(length = 50)
    private String nationality;
    
    @Column(name = "birth_year")
    private Integer birthYear;
    
    @Column(length = 100)
    private String email;
    
    @Column(length = 500)
    private String biography;
    
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Book> books = new ArrayList<>();
    
    // Helper method to add book
    public void addBook(Book book) {
        books.add(book);
        book.setAuthor(this);
    }
    
    // Helper method to remove book
    public void removeBook(Book book) {
        books.remove(book);
        book.setAuthor(null);
    }
}