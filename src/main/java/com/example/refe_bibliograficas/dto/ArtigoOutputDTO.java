package com.example.refe_bibliograficas.dto;

import java.util.List;

public class ArtigoOutputDTO {

    private Long id;
    private String titulo;
    private int anoPublicacao;
    private List<Long> autoresIds;
    private RevistaCientificaOutputDTO revista;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public List<Long> getAutoresIds() {
        return autoresIds;
    }

    public void setAutoresIds(List<Long> autoresIds) {
        this.autoresIds = autoresIds;
    }

    public RevistaCientificaOutputDTO getRevista() {
        return revista;
    }

    public void setRevista(RevistaCientificaOutputDTO revista) {
        this.revista = revista;
    }
}
