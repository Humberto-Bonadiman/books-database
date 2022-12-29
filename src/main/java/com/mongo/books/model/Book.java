package com.mongo.books.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private String id;

    private String title;
    private List<String> authors;
    private Integer numberPages;
    private String bookPublisher;
    private LocalDate publicationDate;

    public Book (
            String title,
            List<String> authors,
            Integer numberPages,
            String bookPublisher,
            LocalDate publicationDate
    ) {
        this.title = title;
        this.authors = authors;
        this.numberPages = numberPages;
        this.bookPublisher = bookPublisher;
        this.publicationDate = publicationDate;
    }
}
