package com.mongo.books.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class BookDto {
    private String title;
    private List<String> authors;
    private Integer numberPages;
    private String bookPublisher;
    private LocalDate publicationDate;
}
