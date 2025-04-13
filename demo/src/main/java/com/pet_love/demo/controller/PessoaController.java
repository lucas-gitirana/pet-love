package com.pet_love.demo.controller;

import com.pet_love.demo.model.dto.PessoaDTO;
import com.pet_love.demo.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PessoaController{

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/pessoas")
    public List<PessoaDTO> getAllPessoas() {
        return pessoaService.getAllPessoas();
    }

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<PessoaDTO> getPessoaById(@PathVariable Long id) {
        return pessoaService.getPessoaById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada com ID: " + id));
    }

    @PostMapping("/pessoas")
    public ResponseEntity<PessoaDTO> addPessoa(@RequestBody PessoaDTO pessoaDTO) {
        PessoaDTO savedPessoa = pessoaService.savePessoa(pessoaDTO);
        return new ResponseEntity<>(savedPessoa, HttpStatus.CREATED);
    }

    @PutMapping("/pessoas/{id}")
    public ResponseEntity<PessoaDTO> editPessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO) {
        PessoaDTO existingPessoa = pessoaService.getPessoaById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada com ID: " + id));

        // Atualiza os dados
        pessoaDTO.setId(id); // Garante que o ID seja mantido
        PessoaDTO updatedPessoa = pessoaService.updatePessoa(pessoaDTO);
        return ResponseEntity.ok(updatedPessoa);
    }

    @DeleteMapping("/pessoas/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        if (pessoaService.getPessoaById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada com ID: " + id);
        }

        pessoaService.deletePessoa(id);
        return ResponseEntity.noContent().build();
    }

}
