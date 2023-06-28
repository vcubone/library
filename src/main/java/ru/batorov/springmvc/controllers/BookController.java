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
import ru.batorov.springmvc.dao.PersonDAO;
import ru.batorov.springmvc.models.Book;
import ru.batorov.springmvc.models.Person;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
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
    public String show(@PathVariable("id") int bookId, Model model, @ModelAttribute("person") Person person) {
        Book book = bookDAO.show(bookId);
        model.addAttribute("book", book);
        
        if (book.getPersonId() == null)
            model.addAttribute("people", personDAO.all());
            else
            model.addAttribute("owner", personDAO.show(book.getPersonId()));
        return "books/show";
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id)
    {
        bookDAO.delete(id);
        return "redirect:/books";
    }
    
    @PatchMapping("/{bookId}/addowner")
    public String addowner(@PathVariable("bookId") int bookId, Model model, @ModelAttribute("person") Person person) {
        bookDAO.addOwner(bookId, person.getPersonId());
        return "redirect:/books/" + bookId;
    }
    
    @PatchMapping("/{id}/release")
    public String deleteOwner(@PathVariable("id") int id)
    {
        bookDAO.deleteOwner(id);
        return "redirect:/books/" + id;
    }
}
