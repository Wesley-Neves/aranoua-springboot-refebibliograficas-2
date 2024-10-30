package com.example.refe_bibliograficas.service;

import com.example.refe_bibliograficas.repository.RevistaCientificaRepository;
import com.example.refe_bibliograficas.model.RevistaCientifica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RevistaCientificaService {

    @Autowired
    private RevistaCientificaRepository revistaCientificaRepository;

    public List<RevistaCientifica> listarTodas(){
        return revistaCientificaRepository.findAll();
    }

    public Optional<RevistaCientifica> encontrarPOrId(Long id){
        return revistaCientificaRepository.findById(id);
    }

    public RevistaCientifica salvar(RevistaCientifica revistaCientifica){
        return revistaCientificaRepository.save(revistaCientifica);
    }

    public void deletar(Long id){
        revistaCientificaRepository.deleteById(id);
    }
}
