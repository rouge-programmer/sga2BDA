package com.library.book_management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 200)
    private String title;
    
    @Column(unique = true, nullable = false, length = 20)
    private String isbn;
    
    @Column(name = "publication_year")
    private Integer publicationYear;
    
    @Column(length = 50)
    private String genre;
    
    @Column(name = "page_count")
    private Integer pageCount;
    
    @Column(length = 1000)
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    @ToString.Exclude
    private Author author;
}