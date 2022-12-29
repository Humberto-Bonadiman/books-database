package com.mongo.books.exception.message;

public class BookNotRegisteredException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BookNotRegisteredException() {
        super("Book not found!");
    }
}
