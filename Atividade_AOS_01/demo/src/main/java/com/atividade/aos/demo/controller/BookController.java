package com.atividade.aos.demo.controller;

import com.atividade.aos.demo.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Endpoint para buscar informações de livros da API do Google Books
    @GetMapping("/books/search")
    public String searchBooks(@RequestParam String query) {
        return bookService.fetchBookInfoFromGoogle(query);
    }
}
