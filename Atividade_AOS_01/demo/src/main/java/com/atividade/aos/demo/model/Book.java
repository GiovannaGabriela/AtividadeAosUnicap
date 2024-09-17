package com.atividade.aos.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String isbn;//Número Padrão Internacional de Livro, basicamente o rg do livro, ex: 9780645570625 é o isbn de "shadow of the conqueror"

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Object getTitle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTitle'");
    }

    public void setTitle(Object title2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTitle'");
    }

    public Object getIsbn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIsbn'");
    }

    public void setIsbn(Object isbn2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIsbn'");
    }

}

