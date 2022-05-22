package br.com.zup.edu.livaria;

import org.aspectj.lang.annotation.Before;
import org.hibernate.validator.constraints.ISBN;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class LivroRequest {

    @NotBlank
    private String titulo;

    @NotBlank
    private String isbn;

    @Past @NotNull
    private LocalDate dataPublicacao;

    public LivroRequest(String titulo, String isbn, LocalDate dataPublicacao) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
    }

    public LivroRequest() {
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public Livro toModel() {
        return new Livro(titulo, isbn, dataPublicacao);
    }
}

