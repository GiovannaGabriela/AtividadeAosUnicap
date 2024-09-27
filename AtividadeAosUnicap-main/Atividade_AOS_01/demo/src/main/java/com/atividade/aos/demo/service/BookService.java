package com.atividade.aos.demo.service;

import com.atividade.aos.demo.exception.ResourceNotFoundException;
import com.atividade.aos.demo.model.Author;
import com.atividade.aos.demo.model.Book;
import com.atividade.aos.demo.model.QueryRequest;
import com.atividade.aos.demo.repository.AuthorRepository;
import com.atividade.aos.demo.repository.BookRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final RestTemplate restTemplate;

    @Value("${external.api.url}")
    private String externalApiUrl;

    @Value("${google.books.api.key}")
    private String apiKey;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, RestTemplate restTemplate) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.restTemplate = restTemplate;
    }

    // Método que faz a requisição à API do Google Books e salva os livros no banco de dados
    public void importBooksFromGoogle(String query) {
        String url = externalApiUrl + "?q=" + query + "&key=" + apiKey;
        String response = restTemplate.getForObject(url, String.class);
        
        // Salva os livros no banco de dados
        List<Book> books = parseBooksFromResponse(response);
        for (Book book : books) {
            bookRepository.save(book); // Salvar cada livro no banco de dados
        }
    }

    // Método para converter a resposta da API do Google Books em objetos Book
    private List<Book> parseBooksFromResponse(String response) {
        List<Book> books = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode items = root.path("items");

            // Itera sobre os itens
            if (items.isArray()) {
                for (JsonNode item : items) {
                    String title = item.path("volumeInfo").path("title").asText();
                    String isbn = item.path("volumeInfo").path("industryIdentifiers").get(0).path("identifier").asText();
                    List<Author> authorList = new ArrayList<>();
                    JsonNode authors = item.path("volumeInfo").path("authors");

                    for (JsonNode authorNode : authors) {
                        String authorName = authorNode.asText();
                        Author author = authorRepository.findByName(authorName).stream().findFirst().orElseGet(() -> {
                            Author newAuthor = new Author();
                            newAuthor.setName(authorName);
                            return authorRepository.save(newAuthor);
                        });
                        authorList.add(author);
                    }

                    // Cria e salva o livro
                    Book book = new Book();
                    book.setTitle(title);
                    book.setIsbn(isbn);
                    book.setAuthors(authorList);
                    books.add(book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void importBooks(QueryRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'importBooks'");
    }
}
