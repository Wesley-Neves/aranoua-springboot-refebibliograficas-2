package com.example.refe_bibliograficas.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "revistas_cientificas")
public class RevistaCientifica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String issn;

    @OneToMany(mappedBy = "revistaCientifica")
    private List<Artigo> artigos;


    public RevistaCientifica() {

    }

    public RevistaCientifica(Long id, String nome, String issn, List<Artigo> artigos) {
        this.id = id;
        this.nome = nome;
        this.issn = issn;
        this.artigos = artigos;
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

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public List<Artigo> getArtigos() {
        return artigos;
    }

    public void setArtigos(List<Artigo> artigos) {
        this.artigos = artigos;
    }

    @Override
    public String toString() {
        return "RevistaCientifica{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", issn='" + issn + '\'' +
                ", artigos=" + artigos +
                '}';
    }
}
