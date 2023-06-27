package ru.batorov.springmvc.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.batorov.springmvc.dao.PersonDAO;
import ru.batorov.springmvc.models.Person;



@Component
public class PersonValidator implements Validator{

    private PersonDAO personDAO;
    
    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    //показывает к какой сущности относится валидатор
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        //есть ли человек с таким же ФИО
        Optional<Person> opPerson = personDAO.show(person.getFullName());
        if (opPerson.isPresent() && person.getPersonId() != opPerson.get().getPersonId())
        {
            errors.rejectValue("fullName", "", "this full_name is already taken");
        }

    }  
}
