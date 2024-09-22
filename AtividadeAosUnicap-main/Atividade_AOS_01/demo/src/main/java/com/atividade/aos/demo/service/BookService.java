package com.atividade.aos.demo.service;

import com.atividade.aos.demo.exception.ResourceNotFoundException;
import com.atividade.aos.demo.model.Book;
import com.atividade.aos.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final RestTemplate restTemplate;

    @Value("${external.api.url}")
    private String externalApiUrl;

    @Value("${google.books.api.key}")
    private String apiKey;

    public BookService(BookRepository bookRepository, RestTemplate restTemplate) {
        this.bookRepository = bookRepository;
        this.restTemplate = restTemplate;
    }

    // Método que faz a requisição à API do Google Books
    public String fetchBookInfoFromGoogle(String query) {
        String url = externalApiUrl + "?q=" + query + "&key=" + apiKey;
        String response = restTemplate.getForObject(url, String.class);

        // Salva os livros no banco de dados
        List<Book> books = parseBooksFromResponse(response);
        for (Book book : books) {
            bookRepository.save(book); // Salvar cada livro no banco de dados
        }
        return response; // Retorna a resposta original, se necessário
    }

    private List<Book> parseBooksFromResponse(String response) {
        List<Book> books = new ArrayList<>();
        return books; 
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
