package ru.batorov.springmvc.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ru.batorov.springmvc.models.Book;
import ru.batorov.springmvc.models.Person;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> all() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));//имена полей в классе и в бд одинаковые, тогда есть готовый rowmapper
    }
    
    public Optional<Person> show(String fullName) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE fullName=?", new BeanPropertyRowMapper<>(Person.class), fullName).stream().findAny();
    }
    
    public Person show(int person_id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE personId=?", new BeanPropertyRowMapper<>(Person.class), person_id).stream().findAny().orElse(null);
    }
    
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(fullName, age) VALUES(?, ?)", person.getFullName(), person.getAge());        
    }
    
    public void update(int person_id, Person updatedperson)
    {
        jdbcTemplate.update("UPDATE Person SET fullName=?, age=? WHERE personId=?", updatedperson.getFullName(), updatedperson.getAge(),  person_id);
    }
    
    public void delete(int personId)
    {
        jdbcTemplate.update("DELETE FROM Person WHERE personId=?", personId);
    }
    
    public List<Book> giveBooks(int personId) {
        return jdbcTemplate.query("SELECT * FROM book WHERE personId=?", new BeanPropertyRowMapper<>(Book.class), personId);
    }
}
