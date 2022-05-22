package br.com.zup.edu.livaria;

import org.hibernate.validator.constraints.ISBN;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Livro {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String titulo;

    @Column(unique = true)
    private String isbn;

    @NotNull
    private LocalDate dataPublicacao;

    public Livro(String titulo, String isbn, LocalDate dataPublicacao) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
    }

    @Deprecated
    public Livro() {
    }

    public Long getId() {
        return id;
    }


}
