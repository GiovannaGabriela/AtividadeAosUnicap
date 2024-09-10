package com.atividade.aos.demo.controller;


import com.example.library.service.BookService;
import org.springframework.web.bind.annotation.*;

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
