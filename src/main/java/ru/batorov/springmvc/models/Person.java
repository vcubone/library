package ru.batorov.springmvc.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Person {
    private int personId;
    
    @NotEmpty(message = "name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Name between 2 and 30")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "ФИО должно быть в формате: Фамилия Имя Отчество")
    private String fullName;
    @Min(value = 0, message = "Age > 0")
    private int age;
    
    public Person() {
    }
    public Person(int personId,
            @NotEmpty(message = "name shouldn't be empty") @Size(min = 2, max = 30, message = "Name between 2 and 30") String fullName,
            @Min(value = 0, message = "Age > 0") int age) {
        this.personId = personId;
        this.fullName = fullName;
        this.age = age;
    }
    public int getPersonId() {
        return personId;
    }
    public void setPersonId(int personId) {
        this.personId = personId;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
