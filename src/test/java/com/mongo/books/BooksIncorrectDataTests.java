package com.mongo.books;

import com.mongo.books.model.Book;
import com.mongo.books.repository.BooksRepository;
import com.mongo.books.utils.CreateBook;
import com.mongo.books.utils.ReturnBook;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BooksIncorrectDataTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BooksRepository booksRepository;

    @BeforeEach
    void setUp() throws Exception {
        booksRepository.deleteAll();
    }

    @AfterEach
    void remove() throws Exception {
        booksRepository.deleteAll();
    }

    @Test
    @Order(6)
    @DisplayName("6 - Return status 400 without title while create book")
    void withoutTitle() throws Exception {
        Book book = new Book();
        List<String> authors = new ArrayList<>();
        authors.add("authors test");
        book.setAuthors(authors);
        book.setNumberPages(150);
        mockMvc.perform(post("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(7)
    @DisplayName("7 - Return status 400 without authors while create book")
    void withoutAuthors() throws Exception {
        Book book = new Book();
        book.setTitle("Title Test");
        book.setNumberPages(150);
        mockMvc.perform(post("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        Book secondBook = new Book();
        List<String> authors = new ArrayList<>();
        secondBook.setTitle("Title Test");
        secondBook.setAuthors(authors);
        secondBook.setNumberPages(150);
        mockMvc.perform(post("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(secondBook)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(8)
    @DisplayName("8 - Return status 400 without number of pages while create book")
    void withoutNumberOfPages() throws Exception {
        Book book = new Book();
        book.setTitle("Title Test");
        List<String> authors = new ArrayList<>();
        authors.add("authors test");
        book.setAuthors(authors);
        mockMvc.perform(post("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(9)
    @DisplayName("9 - Return status 404 if id is not found")
    void idNotFound() throws Exception {
        Book book = CreateBook.createBook();
        booksRepository.save(book);
        mockMvc.perform(get("/book/" + book.getId() + "abc145")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(10)
    @DisplayName("10 - Return status 404 if id is not found when update")
    void idNotFoundWhenUpdate() throws Exception {
        Book book = CreateBook.createBook();
        booksRepository.save(book);
        ReturnBook updateBook = new ReturnBook(
                book.getTitle(),
                book.getAuthors(),
                book.getNumberPages(),
                book.getBookPublisher(),
                book.getPublicationDate().toString()
        );
        mockMvc.perform(patch("/book/" + book.getId() + "abc145")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updateBook)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(11)
    @DisplayName("11 - Return status 404 if id is not found when delete")
    void idNotFoundWhenDelete() throws Exception {
        Book book = CreateBook.createBook();
        booksRepository.save(book);
        mockMvc.perform(delete("/book/" + book.getId() + "abc145"))
                .andExpect(status().isNotFound());
    }
}
