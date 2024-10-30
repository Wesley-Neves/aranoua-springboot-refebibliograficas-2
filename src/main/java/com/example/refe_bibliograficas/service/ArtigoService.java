package com.example.refe_bibliograficas.service;

import com.example.refe_bibliograficas.repository.ArtigoRepository;
import com.example.refe_bibliograficas.model.Artigo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtigoService {

    @Autowired
    private ArtigoRepository artigoRepository;

    public List<Artigo> listarTodos(){
        return artigoRepository.findAll();
    }

    public Optional<Artigo> encontrarPorId(Long id){
        return artigoRepository.findById(id);
    }

    public Artigo salvar(Artigo artigo){
        return artigoRepository.save(artigo);
    }

    public void deletar(Long id){
        artigoRepository.deleteById(id);
    }
}
