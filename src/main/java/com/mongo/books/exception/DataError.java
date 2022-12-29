package com.mongo.books.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataError {
    private String message;

    public DataError(String message) {
        this.message = message;
    }
}
