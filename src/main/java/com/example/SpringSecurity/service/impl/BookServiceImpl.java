package com.example.SpringSecurity.service.impl;

import com.example.SpringSecurity.dto.BookDto;
import com.example.SpringSecurity.entity.Book;
import com.example.SpringSecurity.exception.NotFoundException;
import com.example.SpringSecurity.repository.BookRepository;
import com.example.SpringSecurity.service.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    private final ModelMapper modelMapper;

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book createBook = modelMapper.map(bookDto, Book.class);
        return modelMapper.map(bookRepository.save(createBook), BookDto.class);
    }

    @Override
    public BookDto getBookById(Long bookId) {
        return modelMapper.map(bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Книгу з таким id не знайдено")), BookDto.class);
    }

    @Override
    public List<BookDto> getAllBooks() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false)
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
