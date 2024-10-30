package com.example.refe_bibliograficas.controller;

import com.example.refe_bibliograficas.dto.RevistaCientificaInputDTO;
import com.example.refe_bibliograficas.dto.RevistaCientificaOutputDTO;
import com.example.refe_bibliograficas.model.Artigo;
import com.example.refe_bibliograficas.model.RevistaCientifica;
import com.example.refe_bibliograficas.service.RevistaCientificaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/revistas")
public class RevistaCientificaContoller {

    @Autowired
    private RevistaCientificaService revistaCientificaService;

    @GetMapping
    public List<RevistaCientificaOutputDTO> listarTodas(){
        return revistaCientificaService.listarTodas().stream()
                .map(this::convertToOutputDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RevistaCientificaOutputDTO> buscarPorId(@PathVariable Long id){
        Optional<RevistaCientifica> revistaOpt = revistaCientificaService.encontrarPOrId(id);
        return revistaOpt.map(revistaCientifica -> ResponseEntity.ok(convertToOutputDTO(revistaCientifica)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RevistaCientificaOutputDTO criar(@RequestBody RevistaCientificaInputDTO revistInputDTO){
        RevistaCientifica revista = convertToEntity(revistInputDTO);
        return convertToOutputDTO(revistaCientificaService.salvar(revista));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RevistaCientificaOutputDTO> atualizar(@PathVariable Long id, @RequestBody RevistaCientificaInputDTO revistaInputDTO){
        Optional<RevistaCientifica> revistaOpt = revistaCientificaService.encontrarPOrId(id);
        if (revistaOpt.isPresent()){
            RevistaCientifica revistaAtualizada = convertToEntity(revistaInputDTO);
            revistaAtualizada.setId(id);
            return ResponseEntity.ok(convertToOutputDTO(revistaCientificaService.salvar(revistaAtualizada)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if (revistaCientificaService.encontrarPOrId(id).isPresent()){
            revistaCientificaService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    private RevistaCientificaOutputDTO convertToOutputDTO(RevistaCientifica revista){
        RevistaCientificaOutputDTO outputDTO = new RevistaCientificaOutputDTO();
        outputDTO.setId(revista.getId());
        outputDTO.setNome(revista.getNome());
        outputDTO.setIssn(revista.getIssn());
        outputDTO.setArtigosIds(
                revista.getArtigos().stream()
                        .map(Artigo::getId)
                        .collect(Collectors.toList())
        );
        return outputDTO;
    }

    private RevistaCientifica convertToEntity(RevistaCientificaInputDTO revistaDTO){
        RevistaCientifica revista = new RevistaCientifica();
        revista.setNome(revistaDTO.getNome());
        revista.setIssn(revistaDTO.getIssn());

        return revista;
    }
}
