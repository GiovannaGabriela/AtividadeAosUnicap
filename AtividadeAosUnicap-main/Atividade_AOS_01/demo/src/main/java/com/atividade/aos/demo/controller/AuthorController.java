package com.atividade.aos.demo.controller;

import com.atividade.aos.demo.model.Author;
import com.atividade.aos.demo.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    //Método para pegar todos os autores
    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    //Método para pegar os autores pelo seu Id
    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    //Método para Add um novo autor
    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorService.createAuthor(author);
    }

    //Update nos autor
    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable Long id, @RequestBody Author updatedAuthor) {
        return authorService.updateAuthor(id, updatedAuthor);
    }

    // Método para Deletar o autor
    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}

