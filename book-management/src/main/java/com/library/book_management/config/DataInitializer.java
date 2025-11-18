package com.library.book_management.config;

import lombok.Builder;
import com.library.book_management.entity.Author;
import com.library.book_management.entity.Book;
import com.library.book_management.repository.AuthorRepository;
import com.library.book_management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Builder
@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private AuthorRepository authorRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Create 10 Authors
        Author author1 = Author.builder()
            .name("George Orwell")
            .nationality("British")
            .birthYear(1903)
            .email("g.orwell@classic.com")
            .biography("English novelist and essayist, journalist and critic")
            .build();
        
        Author author2 = Author.builder()
            .name("Jane Austen")
            .nationality("British")
            .birthYear(1775)
            .email("j.austen@classic.com")
            .biography("English novelist known for her six major novels")
            .build();
        
        Author author3 = Author.builder()
            .name("F. Scott Fitzgerald")
            .nationality("American")
            .birthYear(1896)
            .email("f.fitzgerald@classic.com")
            .biography("American novelist and short story writer")
            .build();
        
        Author author4 = Author.builder()
            .name("Harper Lee")
            .nationality("American")
            .birthYear(1926)
            .email("h.lee@classic.com")
            .biography("American novelist best known for To Kill a Mockingbird")
            .build();
        
        Author author5 = Author.builder()
            .name("J.K. Rowling")
            .nationality("British")
            .birthYear(1965)
            .email("jk.rowling@modern.com")
            .biography("British author, best known for Harry Potter series")
            .build();
        
        Author author6 = Author.builder()
            .name("Ernest Hemingway")
            .nationality("American")
            .birthYear(1899)
            .email("e.hemingway@classic.com")
            .biography("American novelist, short-story writer, and journalist")
            .build();
        
        Author author7 = Author.builder()
            .name("Agatha Christie")
            .nationality("British")
            .birthYear(1890)
            .email("a.christie@mystery.com")
            .biography("English writer known for detective novels")
            .build();
        
        Author author8 = Author.builder()
            .name("Stephen King")
            .nationality("American")
            .birthYear(1947)
            .email("s.king@horror.com")
            .biography("American author of horror, supernatural fiction, and fantasy")
            .build();
        
        Author author9 = Author.builder()
            .name("Gabriel García Márquez")
            .nationality("Colombian")
            .birthYear(1927)
            .email("g.marquez@latinlit.com")
            .biography("Colombian novelist and Nobel Prize winner")
            .build();
        
        Author author10 = Author.builder()
            .name("Virginia Woolf")
            .nationality("British")
            .birthYear(1882)
            .email("v.woolf@modern.com")
            .biography("English writer, considered one of the foremost modernists")
            .build();
        
        // Save authors
        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);
        authorRepository.save(author4);
        authorRepository.save(author5);
        authorRepository.save(author6);
        authorRepository.save(author7);
        authorRepository.save(author8);
        authorRepository.save(author9);
        authorRepository.save(author10);
        
        // Create 10 Books
        Book book1 = Book.builder()
            .title("1984")
            .isbn("978-0-452-28423-4")
            .publicationYear(1949)
            .genre("Dystopian Fiction")
            .pageCount(328)
            .description("A dystopian social science fiction novel")
            .author(author1)
            .build();
        
        Book book2 = Book.builder()
            .title("Pride and Prejudice")
            .isbn("978-0-141-43951-8")
            .publicationYear(1813)
            .genre("Romance")
            .pageCount(432)
            .description("A romantic novel of manners")
            .author(author2)
            .build();
        
        Book book3 = Book.builder()
            .title("The Great Gatsby")
            .isbn("978-0-743-27356-5")
            .publicationYear(1925)
            .genre("Literary Fiction")
            .pageCount(180)
            .description("A novel about the American Dream")
            .author(author3)
            .build();
        
        Book book4 = Book.builder()
            .title("To Kill a Mockingbird")
            .isbn("978-0-061-12008-4")
            .publicationYear(1960)
            .genre("Southern Gothic")
            .pageCount(324)
            .description("A novel about racial injustice and childhood innocence")
            .author(author4)
            .build();
        
        Book book5 = Book.builder()
            .title("Harry Potter and the Philosopher's Stone")
            .isbn("978-0-747-53269-9")
            .publicationYear(1997)
            .genre("Fantasy")
            .pageCount(223)
            .description("The first novel in the Harry Potter series")
            .author(author5)
            .build();
        
        Book book6 = Book.builder()
            .title("The Old Man and the Sea")
            .isbn("978-0-684-80122-3")
            .publicationYear(1952)
            .genre("Literary Fiction")
            .pageCount(127)
            .description("A story of an aging fisherman's struggle")
            .author(author6)
            .build();
        
        Book book7 = Book.builder()
            .title("Murder on the Orient Express")
            .isbn("978-0-062-07348-4")
            .publicationYear(1934)
            .genre("Mystery")
            .pageCount(256)
            .description("A detective novel featuring Hercule Poirot")
            .author(author7)
            .build();
        
        Book book8 = Book.builder()
            .title("The Shining")
            .isbn("978-0-385-12167-5")
            .publicationYear(1977)
            .genre("Horror")
            .pageCount(447)
            .description("A horror novel about a haunted hotel")
            .author(author8)
            .build();
        
        Book book9 = Book.builder()
            .title("One Hundred Years of Solitude")
            .isbn("978-0-060-88328-8")
            .publicationYear(1967)
            .genre("Magical Realism")
            .pageCount(417)
            .description("A landmark novel in magical realism")
            .author(author9)
            .build();
        
        Book book10 = Book.builder()
            .title("Mrs Dalloway")
            .isbn("978-0-156-62850-4")
            .publicationYear(1925)
            .genre("Modernist Literature")
            .pageCount(194)
            .description("A novel about a day in the life of Clarissa Dalloway")
            .author(author10)
            .build();
        
        // Save books
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);
        bookRepository.save(book5);
        bookRepository.save(book6);
        bookRepository.save(book7);
        bookRepository.save(book8);
        bookRepository.save(book9);
        bookRepository.save(book10);
        
        System.out.println("Sample data initialized successfully!");
    }
}