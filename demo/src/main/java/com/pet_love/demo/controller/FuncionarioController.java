package com.pet_love.demo.controller;

import com.pet_love.demo.model.dto.FuncionarioDTO;
import com.pet_love.demo.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/funcionarios")
    public List<FuncionarioDTO> getAllFuncionarios() {
        return funcionarioService.getAllFuncionarios();
    }

    @GetMapping("/funcionarios/{id}")
    public ResponseEntity<FuncionarioDTO> getFuncionarioById(@PathVariable Long id) {
        return funcionarioService.getFuncionarioById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado com ID: " + id));
    }

    @PostMapping("/funcionarios")
    public ResponseEntity<FuncionarioDTO> addFuncionario(@RequestBody FuncionarioDTO funcionarioDTO) {
        FuncionarioDTO savedFuncionario = funcionarioService.saveFuncionario(funcionarioDTO);
        return new ResponseEntity<>(savedFuncionario, HttpStatus.CREATED);
    }

    @PutMapping("/funcionarios/{id}")
    public ResponseEntity<FuncionarioDTO> editFuncionario(@PathVariable Long id, @RequestBody FuncionarioDTO funcionarioDTO) {
        funcionarioService.getFuncionarioById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado com ID: " + id));

        // Atualiza os dados
        funcionarioDTO.setId(id);
        FuncionarioDTO updatedFuncionario = funcionarioService.updateFuncionario(funcionarioDTO);
        return ResponseEntity.ok(updatedFuncionario);
    }

    @DeleteMapping("/funcionarios/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable Long id) {
        if (funcionarioService.getFuncionarioById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado com ID: " + id);
        }
        funcionarioService.deleteFuncionario(id);
        return ResponseEntity.noContent().build();
    }

}
