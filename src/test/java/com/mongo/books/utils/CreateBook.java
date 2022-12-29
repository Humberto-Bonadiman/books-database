package com.mongo.books.utils;

import com.mongo.books.dto.BookDto;
import com.mongo.books.model.Book;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateBook {
    public static @NotNull ReturnBook createReturnBook() {
        List<String> authors = new ArrayList<>();
        authors.add("Test author");
        ReturnBook book = new ReturnBook(
                "Test Book",
                authors,
                150,
                "Test Book Publisher",
                "2022-10-29"
        );
        return book;
    }

    public static @NotNull Book createBook() {
        List<String> authors = new ArrayList<>();
        authors.add("Test author");
        Book book = new Book(
                "Test Book",
                authors,
                150,
                "Test Book Publisher",
                LocalDate.now()
        );
        return book;
    }

    public static @NotNull Book createSecondBook() {
        List<String> authors = new ArrayList<>();
        authors.add("Test author 2");
        Book book = new Book(
                "Test Book 2",
                authors,
                160,
                "Test Book Publisher 2",
                LocalDate.now()
        );
        return book;
    }
}
