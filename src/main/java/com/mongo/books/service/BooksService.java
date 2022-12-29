package com.mongo.books.service;

import com.mongo.books.dto.BookDto;
import com.mongo.books.exception.message.BookNotRegisteredException;
import com.mongo.books.exception.message.NotNullException;
import com.mongo.books.model.Book;
import com.mongo.books.repository.BooksRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService implements BooksInterface {

    @Autowired
    BooksRepository booksRepository;

    @Override
    public Book create(BookDto bookDto) {
        checkIfNull(bookDto);
        Book book = new Book(
                bookDto.getTitle(),
                bookDto.getAuthors(),
                bookDto.getNumberPages(),
                bookDto.getBookPublisher(),
                bookDto.getPublicationDate()
        );
        booksRepository.save(book);
        return book;
    }

    @Override
    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    @Override
    public Book findById(String id) {
        return findBookById(id);
    }

    @Override
    public Book update(String id, BookDto bookDto) {
        checkIfNull(bookDto);
        Book updateBook = findBookById(id);
        updateBook.setTitle(bookDto.getTitle());
        updateBook.setAuthors(bookDto.getAuthors());
        updateBook.setNumberPages(bookDto.getNumberPages());
        updateBook.setBookPublisher(bookDto.getBookPublisher());
        updateBook.setPublicationDate(bookDto.getPublicationDate());
        return updateBook;
    }

    @Override
    public void delete(String id) {
        findBookById(id);
        booksRepository.deleteById(id);
    }

    private @NotNull Book findBookById(String id) {
        Optional<Book> validBook = booksRepository.findById(id);
        if (validBook.isEmpty()) {
            throw new BookNotRegisteredException();
        }
        return validBook.get();
    }

    private void checkIfNull(@NotNull BookDto bookDto) {
        if (bookDto.getTitle() == null
                || bookDto.getAuthors() == null
                || bookDto.getAuthors().size() == 0
                || bookDto.getNumberPages() == null
        ) {
            throw new NotNullException();
        }
    }
}
