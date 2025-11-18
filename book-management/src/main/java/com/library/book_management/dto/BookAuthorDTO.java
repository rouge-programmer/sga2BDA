package com.library.book_management.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookAuthorDTO {
    private Long bookId;
    private String bookTitle;
    private String isbn;
    private Integer publicationYear;
    private String genre;
    private Integer pageCount;
    private String description;
    private Long authorId;
    private String authorName;
    private String nationality;
    private Integer birthYear;
    private String email;
}