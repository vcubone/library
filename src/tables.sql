create table person(
    personId int AUTO_INCREMENT,
    PRIMARY KEY(personId),
    fullName varchar(100) not null UNIQUE,
    age int not null
);
create table book(
    bookId int AUTO_INCREMENT,
    PRIMARY KEY(bookId),
    personId int,
    FOREIGN KEY(personId) REFERENCES person2(personId) on DELETE set null,
    title varchar(100) not null,
    author varchar(100) not null,
    releaseYear int not null
);