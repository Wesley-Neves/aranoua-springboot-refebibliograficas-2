package com.example.refe_bibliograficas.controller;

import com.example.refe_bibliograficas.dto.AfiliacaoOutputDTO;
import com.example.refe_bibliograficas.dto.AutorInputDTO;
import com.example.refe_bibliograficas.dto.AutorOutputDTO;
import com.example.refe_bibliograficas.model.Afiliacao;
import com.example.refe_bibliograficas.model.Artigo;
import com.example.refe_bibliograficas.service.AutorService;
import com.example.refe_bibliograficas.model.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    @Autowired
    private  AutorService autorService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AutorOutputDTO> listarTodos(){
        return autorService.listarTodos().stream()
                .map(this::convertToOutputDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AutorOutputDTO> buscarPoId(@PathVariable Long id){
        Optional<Autor> autorOpt = autorService.encontrarPorId(id);
        return autorOpt.map(autor -> ResponseEntity.ok(convertToOutputDTO(autor)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AutorOutputDTO criar(@RequestBody AutorInputDTO autorInputDTO){
        Autor autor = convertToEntity(autorInputDTO);
        return convertToOutputDTO(autorService.salvar(autor));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AutorOutputDTO> atualizar(@PathVariable Long id, @RequestBody AutorInputDTO autorInputDTO){
        Optional<Autor> autorOpt = autorService.encontrarPorId(id);
        if (autorOpt.isPresent()){
            Autor autorAtualizado = convertToEntity(autorInputDTO);
            autorAtualizado.setId(id);
            return ResponseEntity.ok(convertToOutputDTO(autorService.salvar(autorAtualizado)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deletar (@PathVariable Long id){
        if (autorService.encontrarPorId(id).isPresent()){
            autorService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private AutorOutputDTO convertToOutputDTO(Autor autor){
        AutorOutputDTO outputDTO = new AutorOutputDTO();
        outputDTO.setId(autor.getId());
        outputDTO.setNome(autor.getNome());
        outputDTO.setAfiliacao(convertToAfiliacaoOutPutDTO(autor.getAfiliacao()));
        outputDTO.setArtigosIds(
                autor.getArtigos().stream()
                        .map(Artigo::getId)
                        .collect(Collectors.toList())
        );
        return outputDTO;
    }

    private Autor convertToEntity(AutorInputDTO autorInputDTO){
        Autor autor = new Autor();
        autor.setNome(autorInputDTO.getNome());

        return autor;
    }

    private AfiliacaoOutputDTO convertToAfiliacaoOutPutDTO(Afiliacao afiliacao){
        if (afiliacao == null) return null;
        AfiliacaoOutputDTO outputDTO = new AfiliacaoOutputDTO();
        outputDTO.setId(afiliacao.getId());
        outputDTO.setNome(afiliacao.getNome());
        outputDTO.setSigla(afiliacao.getSigla());
        outputDTO.setReferencia(afiliacao.getReferencia());
        return outputDTO;
    }

}
