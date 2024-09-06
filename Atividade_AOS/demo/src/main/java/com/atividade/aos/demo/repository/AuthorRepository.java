package com.atividade.aos.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atividade.aos.demo.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long>{
}


