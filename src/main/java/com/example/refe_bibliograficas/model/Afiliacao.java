package com.example.refe_bibliograficas.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "afiliacoes")
public class Afiliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sigla;

    @Column(nullable = false)
    private String referencia;

    @OneToMany(mappedBy = "afiliacao")
    private List<Autor> autores;

    public Afiliacao() {

    }

    public Afiliacao(Long id, String nome, String sigla, String referencia, List<Autor> autores) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.referencia = referencia;
        this.autores = autores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return "Afiliacao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sigla='" + sigla + '\'' +
                ", referencia='" + referencia + '\'' +
                ", autores=" + autores +
                '}';
    }
}
