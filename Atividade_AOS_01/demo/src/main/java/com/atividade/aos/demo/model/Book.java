package com.atividade.aos.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String isbn; // Número Padrão Internacional de Livro

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
