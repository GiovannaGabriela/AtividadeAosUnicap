package com.atividade.aos.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atividade.aos.demo.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);
}
