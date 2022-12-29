package com.mongo.books.exception.message;

public class NotNullException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotNullException() {
        super("Title, authors and number of pages cannot be null");
    }
}
