package com.library.book_management.controller;

import com.library.book_management.entity.Book;
import com.library.book_management.service.AuthorService;
import com.library.book_management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private AuthorService authorService;
    
    // Display all books
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books/list";
    }
    
    // Display books with authors (INNER JOIN)
    @GetMapping("/with-authors")
    public String listBooksWithAuthors(Model model) {
        model.addAttribute("booksWithAuthors", 
            bookService.getAllBooksWithAuthors());
        return "books/list-with-authors";
    }
    
    // Show form to create new book
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "books/form";
    }
    
    // Handle book creation
    @PostMapping
    public String createBook(@ModelAttribute Book book, 
                            @RequestParam Long authorId,
                            RedirectAttributes redirectAttributes) {
        try {
            bookService.saveBook(book, authorId);
            redirectAttributes.addFlashAttribute("success", 
                "Book created successfully!");
            return "redirect:/books";
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", 
                "Error: Duplicate ISBN or data integrity violation!");
            return "redirect:/books/new";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Error creating book: " + e.getMessage());
            return "redirect:/books/new";
        }
    }
    
    // Show form to edit book
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model,
                              RedirectAttributes redirectAttributes) {
        try {
            Book book = bookService.getBookById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
            model.addAttribute("book", book);
            model.addAttribute("authors", authorService.getAllAuthors());
            return "books/form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Book not found with id: " + id);
            return "redirect:/books";
        }
    }
    
    // Handle book update
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, 
                            @ModelAttribute Book book,
                            @RequestParam Long authorId,
                            RedirectAttributes redirectAttributes) {
        try {
            bookService.updateBook(id, book, authorId);
            redirectAttributes.addFlashAttribute("success", 
                "Book updated successfully!");
            return "redirect:/books";
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", 
                "Error: Duplicate ISBN or data integrity violation!");
            return "redirect:/books/edit/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Error updating book: " + e.getMessage());
            return "redirect:/books/edit/" + id;
        }
    }
    
    // Delete book
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, 
                            RedirectAttributes redirectAttributes) {
        try {
            bookService.deleteBook(id);
            redirectAttributes.addFlashAttribute("success", 
                "Book deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Error deleting book: " + e.getMessage());
        }
        return "redirect:/books";
    }
}