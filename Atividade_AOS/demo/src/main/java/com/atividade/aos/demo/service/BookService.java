package com.atividade.aos.demo.service;

import com.atividade.aos.demo.exception.ResourceNotFoundException;
import com.atividade.aos.demo.model.Book;
import com.atividade.aos.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final RestTemplate restTemplate;

    @Value("${external.api.url}")
    private String externalApiUrl;

    public BookService(BookRepository bookRepository, RestTemplate restTemplate) {
        this.bookRepository = bookRepository;
        this.restTemplate = restTemplate;
    }

    public String fetchBookInfoFromGoogle(String query) {
        String url = externalApiUrl + "?q=" + query;
        return restTemplate.getForObject(url, String.class);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book book = getBookById(id);
        book.setTitle(updatedBook.getTitle());
        book.setIsbn(updatedBook.getIsbn());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }
}
