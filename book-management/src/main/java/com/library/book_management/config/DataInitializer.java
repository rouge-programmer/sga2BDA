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
            .name("Mario Puzo")
            .nationality("American")
            .birthYear(1920)
            .email("-")
            .biography("American author and screenwriter")
            .build();
        
        Author author2 = Author.builder()
            .name("Munshi Premchand")
            .nationality("Indian")
            .birthYear(1880)
            .email("-")
            .biography("Munshi Premchand is his pen name")
            .build();
        
        Author author3 = Author.builder()
            .name("Fyodor Dostoevsky")
            .nationality("Russian")
            .birthYear(1821)
            .email("-")
            .biography("Fyodor Mikhailovich Dostoevsky was a Russian novelist, short story writer, essayist and journalist. ")
            .build();
        
        Author author4 = Author.builder()
            .name("Saksham Garg")
            .nationality("Indian")
            .birthYear(1998)
            .email("s.garg@writer.com")
            .biography("Indian mythological fantasy novelist best known for Samsara")
            .build();
        
        Author author5 = Author.builder()
            .name("J.K. Rowling")
            .nationality("British")
            .birthYear(1965)
            .email("jk.rowling@modern.com")
            .biography("British author, best known for Harry Potter series")
            .build();
        
        Author author6 = Author.builder()
            .name("Chetan Bhagat")
            .nationality("Indian")
            .birthYear(1974)
            .email("c.bhagat@writer.com")
            .biography("Chetan Prakash Bhagat is an Indian author, columnist, screenwriter")
            .build();
        
        Author author7 = Author.builder()
            .name("Agatha Christie")
            .nationality("British")
            .birthYear(1890)
            .email("-")
            .biography("English writer known for detective novels")
            .build();
        
        Author author8 = Author.builder()
            .name("Stephen King")
            .nationality("American")
            .birthYear(1947)
            .email("-")
            .biography("American author of horror, supernatural fiction, and fantasy")
            .build();
        
        Author author9 = Author.builder()
            .name("Gabriel García Márquez")
            .nationality("Colombian")
            .birthYear(1927)
            .email("-")
            .biography("Colombian novelist and Nobel Prize winner")
            .build();
        
        Author author10 = Author.builder()
            .name("Virginia Woolf")
            .nationality("British")
            .birthYear(1882)
            .email("-")
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
            .title("The Godfather")
            .isbn("978-0-452-28423-4")
            .publicationYear(1969)
            .genre("Thriller")
            .pageCount(328)
            .description("A burgeoning fascination with the Mafia in American society")
            .author(author1)
            .build();
        
        Book book2 = Book.builder()
            .title("Godaan")
            .isbn("978-0-141-43951-8")
            .publicationYear(1936)
            .genre("Fiction")
            .pageCount(432)
            .description("Godaan is a Hindi novel by Munshi Premchand")
            .author(author2)
            .build();
        
        Book book3 = Book.builder()
            .title("Crime & Punishment")
            .isbn("978-0-743-27356-5")
            .publicationYear(1866)
            .genre("Crime Fiction")
            .pageCount(380)
            .description("A novel about murder and redemption, Crime and Punishment.")
            .author(author3)
            .build();
        
        Book book4 = Book.builder()
            .title("Samsara: Enter the Valley of the Gods")
            .isbn("978-0-061-12008-4")
            .publicationYear(2022)
            .genre("Fantasy fiction")
            .pageCount(324)
            .description("Samsara is a perfect intersection of YA and adult fantasy fiction.")
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
            .title("Half Girlfriend")
            .isbn("978-0-684-80122-3")
            .publicationYear(2014)
            .genre("Romance")
            .pageCount(280)
            .description("Half Girlfriend is an Indian English coming of age, young adult romance")
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