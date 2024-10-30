package com.example.refe_bibliograficas.controller;

import com.example.refe_bibliograficas.dto.ArtigoInputDTO;
import com.example.refe_bibliograficas.dto.ArtigoOutputDTO;
import com.example.refe_bibliograficas.dto.RevistaCientificaOutputDTO;
import com.example.refe_bibliograficas.model.Artigo;
import com.example.refe_bibliograficas.model.Autor;
import com.example.refe_bibliograficas.model.RevistaCientifica;
import com.example.refe_bibliograficas.service.ArtigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/artigos")
public class ArtigoController {

    @Autowired
    private ArtigoService artigoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArtigoOutputDTO> listarTodos(){
        return artigoService.listarTodos().stream()
                .map(this::convertToOutPut)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArtigoOutputDTO> buscarPorId(@PathVariable Long id){
        Optional<Artigo> artigoOpt = artigoService.encontrarPorId(id);
        return artigoOpt.map(artigo -> ResponseEntity.ok(convertToOutPut(artigo)))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ArtigoOutputDTO criar(@RequestBody ArtigoInputDTO artigoInputDTO){
        Artigo artigo = convertToEntity(artigoInputDTO);
        return convertToOutPut(artigoService.salvar(artigo));
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArtigoOutputDTO> atualizar(@PathVariable Long id, @RequestBody ArtigoInputDTO artigoInputDTO){
        Optional<Artigo> artigoOpt = artigoService.encontrarPorId(id);
        if (artigoOpt.isPresent()){
            Artigo artigoAtualizado = convertToEntity(artigoInputDTO);
            artigoAtualizado.setId(id);
            return ResponseEntity.ok(convertToOutPut(artigoService.salvar(artigoAtualizado)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if(artigoService.encontrarPorId(id).isPresent()){
            artigoService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    private ArtigoOutputDTO convertToOutPut(Artigo artigo){
        ArtigoOutputDTO outputDTO = new ArtigoOutputDTO();
        outputDTO.setId(artigo.getId());
        outputDTO.setTitulo(artigo.getTitulo());
        outputDTO.setAnoPublicacao(artigo.getAnoPublicacao());
        outputDTO.setAutoresIds(
                artigo.getAutores().stream()
                        .map(Autor::getId)
                        .collect(Collectors.toList())
        );
        outputDTO.setRevista(convertToRevistaOutPutDTO(artigo.getRevistaCientifica()));
        return outputDTO;
    }

    private Artigo convertToEntity(ArtigoInputDTO artigoInputDTO){
        Artigo artigo = new Artigo();
        artigo.setTitulo(artigoInputDTO.getTitulo());
        artigo.setAnoPublicacao(artigoInputDTO.getAnoPublicacao());

        return artigo;
    }

    private RevistaCientificaOutputDTO convertToRevistaOutPutDTO(RevistaCientifica revista){
        if (revista == null) return null;
        RevistaCientificaOutputDTO outputDTO = new RevistaCientificaOutputDTO();
        outputDTO.setId(revista.getId());
        outputDTO.setNome(revista.getNome());
        outputDTO.setIssn(revista.getIssn());
        return outputDTO;
    }
}
