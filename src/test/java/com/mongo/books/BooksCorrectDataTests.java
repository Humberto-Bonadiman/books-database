package com.mongo.books;

import com.mongo.books.dto.BookDto;
import com.mongo.books.model.Book;
import com.mongo.books.repository.BooksRepository;
import com.mongo.books.utils.CreateBook;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mongo.books.utils.ReturnBook;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class BooksCorrectDataTests {

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
	@Order(1)
	@DisplayName("1 - Must register a book successfully")
	void createBookSuccessfully() throws Exception {
		ReturnBook book = CreateBook.createReturnBook();
		mockMvc.perform(post("/book")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(book)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	@Order(2)
	@DisplayName("2 - Find a book by id")
	void findBookById() throws Exception {
		Book book = CreateBook.createBook();
		booksRepository.save(book);
		mockMvc.perform(get("/book/" + book.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(book)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@Order(3)
	@DisplayName("3 - Show all books")
	void showAllBooks() throws Exception {
		Book book = CreateBook.createBook();
		booksRepository.save(book);
		Book secondBook = CreateBook.createSecondBook();
		booksRepository.save(secondBook);
		mockMvc.perform(get("/book"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].title").value(book.getTitle()))
				.andExpect(jsonPath("$[0].authors[0]").value(book.getAuthors().get(0)))
				.andExpect(jsonPath("$[0].numberPages").value(book.getNumberPages()))
				.andExpect(jsonPath("$[0].bookPublisher").value(book.getBookPublisher()))
				.andExpect(jsonPath("$[0].publicationDate")
						.value(book.getPublicationDate().toString()))
				.andExpect(jsonPath("$[1].title").value(secondBook.getTitle()))
				.andExpect(jsonPath("$[1].authors[0]").value(secondBook.getAuthors().get(0)))
				.andExpect(jsonPath("$[1].numberPages").value(secondBook.getNumberPages()))
				.andExpect(jsonPath("$[1].bookPublisher").value(secondBook.getBookPublisher()))
				.andExpect(jsonPath("$[1].publicationDate")
						.value(secondBook.getPublicationDate().toString()));
	}

	@Test
	@Order(4)
	@DisplayName("4 - Update a book by id")
	void updateBookById() throws Exception {
		Book book = CreateBook.createBook();
		booksRepository.save(book);
		ReturnBook updateBook = new ReturnBook(
				book.getTitle(),
				book.getAuthors(),
				book.getNumberPages(),
				book.getBookPublisher(),
				book.getPublicationDate().toString()
		);
		mockMvc.perform(patch("/book/" + book.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(updateBook)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@Order(5)
	@DisplayName("5 - Delete a book by id")
	void deleteBookById() throws Exception {
		Book book = CreateBook.createBook();
		booksRepository.save(book);
		mockMvc.perform(delete("/book/" + book.getId()))
				.andExpect(status().isNoContent());
	}
}
