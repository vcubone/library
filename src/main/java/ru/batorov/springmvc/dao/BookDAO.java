package ru.batorov.springmvc.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ru.batorov.springmvc.models.Book;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<Book> all() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }
    
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(title, author, releaseYear) VALUES(?, ?, ?)", book.getTitle(), book.getAuthor(), book.getReleaseYear());        
    }
    
    public Book show(int bookId) {
        return jdbcTemplate.query("SELECT * FROM book WHERE bookId=?", new BeanPropertyRowMapper<>(Book.class), bookId).stream().findAny().orElse(null);
    }
    
    public void update(int bookId, Book updatedBook)
    {
        jdbcTemplate.update("UPDATE book SET title=?, author=?, releaseYear=? WHERE bookId=?", updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getReleaseYear(),  bookId);
    }
    
    public void delete(int bookId)
    {
        jdbcTemplate.update("DELETE FROM book WHERE bookId=?", bookId);
    }
    
    public void addOwner(int bookId, int personId)
    {
        jdbcTemplate.update("UPDATE book SET personId=? WHERE bookId=?", personId, bookId);
    }
    
    public void deleteOwner(int bookId)
    {
        jdbcTemplate.update("UPDATE book SET personId=? WHERE bookId=?", null, bookId);
    }
}
