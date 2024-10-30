package com.example.refe_bibliograficas.dto;

import java.util.List;

public class AutorInputDTO {

    private String nome;
    private Long afiliacaoId;
    private List<Long> artigosIds;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getAfiliacaoId() {
        return afiliacaoId;
    }

    public void setAfiliacaoId(Long afiliacaoId) {
        this.afiliacaoId = afiliacaoId;
    }

    public List<Long> getArtigosIds() {
        return artigosIds;
    }

    public void setArtigosIds(List<Long> artigosIds) {
        this.artigosIds = artigosIds;
    }
}
