package com.mongo.books.controller;

import com.mongo.books.dto.BookDto;
import com.mongo.books.model.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/book")
public interface BookInterfaceController {

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content)})
    @Operation(summary = "Create book")
    ResponseEntity<Book> create(@RequestBody BookDto bookDto);

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find all books",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Book.class))) })})
    ResponseEntity<List<Book>> findAll();

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find book by id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)})
    @Operation(summary = "Find book by id")
    ResponseEntity<Book> findById(@PathVariable String id);

    @PatchMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)})
    @Operation(summary = "Update book by id")
    ResponseEntity<Book> update(
            @PathVariable String id,
            @RequestBody BookDto bookDto
    );

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)})
    ResponseEntity<Object> deleteById(@PathVariable String id);
}
