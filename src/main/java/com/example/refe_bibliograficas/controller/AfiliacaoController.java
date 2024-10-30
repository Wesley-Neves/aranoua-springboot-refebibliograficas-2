package com.example.refe_bibliograficas.controller;

import com.example.refe_bibliograficas.dto.AfiliacaoInputDTO;
import com.example.refe_bibliograficas.dto.AfiliacaoOutputDTO;
import com.example.refe_bibliograficas.model.Afiliacao;
import com.example.refe_bibliograficas.model.Autor;
import com.example.refe_bibliograficas.service.Afiliacaoservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/afiliacoes")
public class AfiliacaoController {

    @Autowired
    private Afiliacaoservice afiliacaoservice;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AfiliacaoOutputDTO> listarTodas(){
        return afiliacaoservice.listarTodas().stream()
                .map(this::convertToOutPutDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AfiliacaoOutputDTO> buscarPorId(@PathVariable Long id){
        Optional<Afiliacao> afiliacaoOpt = afiliacaoservice.encontrarPorId(id);
        return afiliacaoOpt.map(afiliacao -> ResponseEntity.ok(convertToOutPutDTO(afiliacao)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AfiliacaoOutputDTO criar(@RequestBody AfiliacaoInputDTO afiliacaoInputDTO){
        Afiliacao afiliacao = convertToEntity(afiliacaoInputDTO);
        return convertToOutPutDTO(afiliacaoservice.salvar(afiliacao));
    }


    private AfiliacaoOutputDTO convertToOutPutDTO(Afiliacao afiliacao){
        AfiliacaoOutputDTO outputDTO = new AfiliacaoOutputDTO();
        outputDTO.setId(afiliacao.getId());
        outputDTO.setNome(afiliacao.getNome());
        outputDTO.setSigla(afiliacao.getSigla());
        outputDTO.setReferencia(afiliacao.getReferencia());
        outputDTO.setAutoresIds(
                afiliacao.getAutores().stream()
                        .map(Autor::getId)
                        .collect(Collectors.toList())
        );
        return outputDTO;
    }

    private Afiliacao convertToEntity(AfiliacaoInputDTO afiliacaoInputDTO){
        Afiliacao afiliacao = new Afiliacao();
        afiliacao.setNome(afiliacaoInputDTO.getNome());
        afiliacao.setSigla(afiliacaoInputDTO.getSigla());
        afiliacao.setReferencia(afiliacaoInputDTO.getReferencia());
        return afiliacao;
    }
}
