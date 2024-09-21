package com.atividade.aos.demo.repository;

import com.atividade.aos.demo.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    // Consulta para buscar autores pelo nome
    List<Author> findByName(String name);
}




