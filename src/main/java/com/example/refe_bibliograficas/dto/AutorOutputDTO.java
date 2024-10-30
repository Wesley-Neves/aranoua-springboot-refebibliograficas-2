package com.example.refe_bibliograficas.dto;

import java.util.List;

public class AutorOutputDTO {

    private Long id;
    private String nome;
    private AfiliacaoOutputDTO afiliacao;
    private List<Long> artigosIds;


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

    public AfiliacaoOutputDTO getAfiliacao() {
        return afiliacao;
    }

    public void setAfiliacao(AfiliacaoOutputDTO afiliacao) {
        this.afiliacao = afiliacao;
    }

    public List<Long> getArtigosIds() {
        return artigosIds;
    }

    public void setArtigosIds(List<Long> artigosIds) {
        this.artigosIds = artigosIds;
    }
}
