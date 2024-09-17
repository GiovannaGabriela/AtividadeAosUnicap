package com.atividade.aos.demo.controller;


import org.springframework.web.bind.annotation.*;

import com.atividade.aos.demo.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/external-search")
    public String searchBooksExternally(@RequestParam String query) {
        return bookService.fetchBookInfoFromGoogle(query);
    }

}
