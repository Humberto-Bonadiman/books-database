package com.mongo.books.controller;

import com.mongo.books.dto.BookDto;
import com.mongo.books.model.Book;
import com.mongo.books.service.BooksService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@Tag(name = "Book")
public class BookController implements BookInterfaceController {

    @Autowired
    BooksService bookService;

    @Override
    public ResponseEntity<Book> create(BookDto bookDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(bookDto));
    }

    @Override
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }

    @Override
    public ResponseEntity<Book> findById(String id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findById(id));
    }

    @Override
    public ResponseEntity<Book> update(String id, BookDto bookDto) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.update(id, bookDto));
    }

    @Override
    public ResponseEntity<Object> deleteById(String id) {
        bookService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
