package com.atividade.aos.demo.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
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

}
