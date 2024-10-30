package com.example.refe_bibliograficas.service;

import com.example.refe_bibliograficas.repository.AutorRepository;
import com.example.refe_bibliograficas.model.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> listarTodos(){
        return autorRepository.findAll();
    }

    public Optional<Autor> encontrarPorId(Long id){
        return autorRepository.findById(id);
    }

    public Autor salvar (Autor autor){
        return autorRepository.save(autor);
    }

    public void deletar(Long id){
        autorRepository.deleteById(id);
    }

}
