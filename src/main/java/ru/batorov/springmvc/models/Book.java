package ru.batorov.springmvc.models;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Component
public class Book {
    private int book_id;
    private int person_id;
    
    @NotEmpty(message = "title shouldn't be empty")
    @Size(min = 2, max = 30, message = "title between 2 and 30")
    private String title;
    @NotEmpty(message = "author shouldn't be empty")
    @Size(min = 2, max = 30, message = "author between 2 and 30")
    private String author;
    
    @Min(value = 0, message = "release_year > 0")
    private int release_year;

    public Book(int book_id, int person_id,
            @NotEmpty(message = "title shouldn't be empty") @Size(min = 2, max = 30, message = "title between 2 and 30") String title,
            @NotEmpty(message = "author shouldn't be empty") @Size(min = 2, max = 30, message = "author between 2 and 30") String author,
            @Min(value = 0, message = "release_year > 0") int release_year) {
        this.book_id = book_id;
        this.person_id = person_id;
        this.title = title;
        this.author = author;
        this.release_year = release_year;
    }

    public Book() {
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }
    
    
}
