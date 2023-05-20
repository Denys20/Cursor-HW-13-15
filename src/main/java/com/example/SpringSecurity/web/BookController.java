package com.example.SpringSecurity.web;

import com.example.SpringSecurity.dto.BookDto;
import com.example.SpringSecurity.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
@SecurityScheme(name = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class BookController {
    private final BookService bookService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityRequirement(name = "jwt")
    public ResponseEntity createBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(bookDto));
    }

    @GetMapping("/book/{id}")
    public ResponseEntity getBookById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookById(id));
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @DeleteMapping("delete/{id}")
    @Operation(description = "API для видалення книги за ID книги")
    public ResponseEntity deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
