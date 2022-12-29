package com.mongo.books.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.mongo.books.exception.message.BookNotRegisteredException;
import com.mongo.books.exception.message.NotNullException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler({
            InvalidFormatException.class,
            NotNullException.class
    })
    public ResponseEntity<Object> handlerBadRequest(@NotNull Exception exception) {
        return new ResponseEntity<>(new DataError(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BookNotRegisteredException.class})
    public ResponseEntity<Object> handlerNotFound(@NotNull Exception exception) {
        return new ResponseEntity<>(new DataError(exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}
