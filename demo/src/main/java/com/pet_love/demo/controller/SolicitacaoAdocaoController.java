package com.pet_love.demo.controller;

import com.pet_love.demo.model.dto.SolicitacaoAdocaoDTO;
import com.pet_love.demo.service.SolicitacaoAdocaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SolicitacaoAdocaoController {

    @Autowired
    private SolicitacaoAdocaoService solicAdocaoService;

    @GetMapping("/solicitacao-adocao")
    public List<SolicitacaoAdocaoDTO> getAllPets() {
        return solicAdocaoService.getAllSolicitacoes();
    }

    @GetMapping("solicitacao-adocao/{id}")
    public ResponseEntity<SolicitacaoAdocaoDTO> getSolicitacaoAdocaoById(@PathVariable Long id) {
        return solicAdocaoService.getSolicitacaoAdocaoById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitação não encontrada com ID: " + id));
    }

    @PostMapping("/solicitacao-adocao")
    public ResponseEntity<SolicitacaoAdocaoDTO> addPet(@RequestBody SolicitacaoAdocaoDTO dto) {
        SolicitacaoAdocaoDTO savedSolicitacao = solicAdocaoService.saveSolicitacaoAdocao(dto);
        return new ResponseEntity<>(savedSolicitacao, HttpStatus.CREATED);
    }

    @PutMapping("/solicitacao-adocao/{id}")
    public ResponseEntity<SolicitacaoAdocaoDTO> editSolicitacao(@PathVariable Long id, @RequestBody SolicitacaoAdocaoDTO dto) {
        solicAdocaoService.getSolicitacaoAdocaoById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitação não encontrada com ID: " + id));

        // Atualiza os dados
        dto.setId(id);
        SolicitacaoAdocaoDTO updatedSolicitacao = solicAdocaoService.updateSolicitacaoAdocao(dto);
        return ResponseEntity.ok(updatedSolicitacao);
    }

    @DeleteMapping("solicitacao-adocao/{id}")
    public ResponseEntity<Void> deleteSolicitacaoAdocao(@PathVariable Long id) {
        if (solicAdocaoService.getSolicitacaoAdocaoById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitação não encontrada com ID: " + id);
        }

        solicAdocaoService.deleteSolicitacaoAdocao(id);
        return ResponseEntity.noContent().build();
    }

}
