package com.library.book_management.controller;

import com.library.book_management.entity.Author;
import com.library.book_management.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    
    @Autowired
    private AuthorService authorService;
    
    // Display all authors
    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "authors/list";
    }
    
    // Show form to create new author
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("author", new Author());
        return "authors/form";
    }
    
    // Handle author creation
    @PostMapping
    public String createAuthor(@ModelAttribute Author author, 
                              RedirectAttributes redirectAttributes) {
        try {
            authorService.saveAuthor(author);
            redirectAttributes.addFlashAttribute("success", 
                "Author created successfully!");
            return "redirect:/authors";
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", 
                "Error: Duplicate email or data integrity violation!");
            return "redirect:/authors/new";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Error creating author: " + e.getMessage());
            return "redirect:/authors/new";
        }
    }
    
    // Show form to edit author
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, 
                              RedirectAttributes redirectAttributes) {
        try {
            Author author = authorService.getAuthorById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
            model.addAttribute("author", author);
            return "authors/form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Author not found with id: " + id);
            return "redirect:/authors";
        }
    }
    
    // Handle author update
    @PostMapping("/update/{id}")
    public String updateAuthor(@PathVariable Long id, 
                              @ModelAttribute Author author,
                              RedirectAttributes redirectAttributes) {
        try {
            authorService.updateAuthor(id, author);
            redirectAttributes.addFlashAttribute("success", 
                "Author updated successfully!");
            return "redirect:/authors";
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", 
                "Error: Duplicate email or data integrity violation!");
            return "redirect:/authors/edit/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Error updating author: " + e.getMessage());
            return "redirect:/authors/edit/" + id;
        }
    }
    
    // Delete author
    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id, 
                              RedirectAttributes redirectAttributes) {
        try {
            authorService.deleteAuthor(id);
            redirectAttributes.addFlashAttribute("success", 
                "Author deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Cannot delete author. They may have associated books.");
        }
        return "redirect:/authors";
    }
}