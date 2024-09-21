package com.atividade.aos.demo.controller;

import com.atividade.aos.demo.model.Book;
import com.atividade.aos.demo.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Endpoint para buscar todos os livros
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // Endpoint para buscar um livro pelo ID
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // Endpoint para criar um novo livro
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    // Endpoint para atualizar um livro existente
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        return bookService.updateBook(id, updatedBook);
    }

    // Endpoint para deletar um livro
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    // Endpoint para buscar informações de livros da API do Google Books
    @GetMapping("/search")
    public String searchBooks(@RequestParam String query) {
        return bookService.fetchBookInfoFromGoogle(query);
    }
}
