package com.mongo.books.service;

import com.mongo.books.dto.BookDto;
import com.mongo.books.model.Book;

import java.util.List;

public interface BooksInterface {
    Book create(BookDto bookDto);

    List<Book> findAll();

    Book findById(String id);

    Book update(String id, BookDto bookDto);

    void delete(String id);
}
