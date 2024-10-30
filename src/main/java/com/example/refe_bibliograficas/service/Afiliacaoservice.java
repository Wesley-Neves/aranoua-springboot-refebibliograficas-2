package com.example.refe_bibliograficas.service;

import com.example.refe_bibliograficas.model.Afiliacao;
import com.example.refe_bibliograficas.repository.AfiliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Afiliacaoservice {

    @Autowired
    private AfiliacaoRepository afiliacaoRepository;

    public List<Afiliacao> listarTodas(){
        return afiliacaoRepository.findAll();
    }

    public Optional<Afiliacao> encontrarPorId(Long id){
        return afiliacaoRepository.findById(id);
    }

    public Afiliacao salvar(Afiliacao afiliacao){
        return afiliacaoRepository.save(afiliacao);
    }

    public void deletar(Long id){
        afiliacaoRepository.deleteById(id);
    }
}
