package com.atividade.aos.demo.service;

import com.atividade.aos.demo.exception.ResourceNotFoundException;
import com.atividade.aos.demo.model.Author;
import com.atividade.aos.demo.repository.AuthorRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found"));
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id, Author updatedAuthor) {
        Author author = getAuthorById(id);
        author.setName(updatedAuthor.getName());
        return authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        Author author = getAuthorById(id);
        authorRepository.delete(author);
    }
}
