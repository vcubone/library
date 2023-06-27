package ru.batorov.springmvc.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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
    
    public Optional<Person> show(String full_name) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE fullName=?", new BeanPropertyRowMapper<>(Person.class), full_name).stream().findAny();
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
}