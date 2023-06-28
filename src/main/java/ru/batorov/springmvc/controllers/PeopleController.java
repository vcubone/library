package ru.batorov.springmvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import ru.batorov.springmvc.util.PersonValidator;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;
    private final PersonValidator personValidator;
    private final BookDAO bookDAO;
    
    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator personValidator, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
        this.bookDAO = bookDAO;
    }
    
    @GetMapping()
    public String all(Model model){
        System.out.println("\n\n\n\nall");
        model.addAttribute("people", personDAO.all());
        return "people/all";
    }
    


    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }
    
    @PostMapping("/new")
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        
        if (bindingResult.hasErrors())
            return "people/new";
        personDAO.save(person);
        return "redirect:/people";
    }
    
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int personId, Model model) {
        model.addAttribute("person", personDAO.show(personId));
        model.addAttribute("books", bookDAO.giveBooks(personId));
        //TODO add books
        return "people/show";
    }
    
    @GetMapping("/{personId}/edit")
    public String edit(@PathVariable("personId") int person_id, Model model) {
        model.addAttribute("person", personDAO.show(person_id));
        return "people/edit";
    }
    
    @PatchMapping("/{personId}/edit")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,@PathVariable("personId") int personId) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/edit";
        personDAO.update(personId, person);
        return "redirect:/people";
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id)
    {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
