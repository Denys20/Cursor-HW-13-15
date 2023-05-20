package com.example.SpringSecurity.service;

import com.example.SpringSecurity.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto createBook(BookDto bookDto);

    BookDto getBookById(Long bookId);

    List<BookDto> getAllBooks();

    void deleteBook(Long bookId);
}
