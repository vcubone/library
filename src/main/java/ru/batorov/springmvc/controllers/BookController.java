package ru.batorov.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import ru.batorov.springmvc.dao.BookDAO;
import ru.batorov.springmvc.models.Book;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    
    
    
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String all(Model model){
        System.out.println("\n\n\n\nall");
        model.addAttribute("books", bookDAO.all());
        return "books/all";
    }
    
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{bookId}/edit")
    public String edit(@PathVariable("bookId") int bookId, Model model) {
        model.addAttribute("book", bookDAO.show(bookId));
        return "books/edit";
    }
    
     @PatchMapping("/{bookId}/edit")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,@PathVariable("bookId") int bookId) {
        if (bindingResult.hasErrors())
            return "books/edit";
        bookDAO.update(bookId, book);
        return "redirect:/books";
    }
    
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int bookId, Model model) {
        model.addAttribute("book", bookDAO.show(bookId));
        //TODO add people
        return "books/show";
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id)
    {
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
