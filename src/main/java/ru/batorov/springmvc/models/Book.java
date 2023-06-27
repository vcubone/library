package ru.batorov.springmvc.models;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Component
public class Book {
    private int bookId;
    private Integer personId;
    
    @NotEmpty(message = "title shouldn't be empty")
    @Size(min = 2, max = 30, message = "title between 2 and 30")
    private String title;
    @NotEmpty(message = "author shouldn't be empty")
    @Size(min = 2, max = 30, message = "author between 2 and 30")
    private String author;
    
    @Min(value = 0, message = "release_year > 0")
    private int releaseYear;

    public Book(int bookId, Integer personId,
            @NotEmpty(message = "title shouldn't be empty") @Size(min = 2, max = 30, message = "title between 2 and 30") String title,
            @NotEmpty(message = "author shouldn't be empty") @Size(min = 2, max = 30, message = "author between 2 and 30") String author,
            @Min(value = 0, message = "release_year > 0") int releaseYear) {
        this.bookId = bookId;
        this.personId = personId;
        this.title = title;
        this.author = author;
        this.releaseYear = releaseYear;
    }

    public Book() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
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

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }


    
}
