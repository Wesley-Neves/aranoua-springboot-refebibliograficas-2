package com.example.refe_bibliograficas.dto;

import java.util.List;

public class ArtigoInputDTO {

    private String titulo;
    private int anoPublicacao;
    private List<Long> autoresIds;
    private Long revistaId;


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

    public Long getRevistaId() {
        return revistaId;
    }

    public void setRevistaId(Long revistaId) {
        this.revistaId = revistaId;
    }
}
