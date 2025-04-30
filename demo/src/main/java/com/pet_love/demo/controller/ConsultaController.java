package com.pet_love.demo.controller;

import com.pet_love.demo.model.dto.ConsultaDTO;
import com.pet_love.demo.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping("/consultas")
    public List<ConsultaDTO> getAllConsultas() {
        return consultaService.getAllConsultas();
    }

    @GetMapping("/consultas/{id}")
    public ResponseEntity<ConsultaDTO> getConsultaById(@PathVariable Long id) {
        return consultaService.getConsultaById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrada com o id: " + id));
    }

    @PostMapping("/consultas")
    public ResponseEntity<ConsultaDTO> addConsulta(@RequestBody ConsultaDTO consultaDTO) {
        ConsultaDTO savedConsulta = consultaService.saveConsulta(consultaDTO);
        return new ResponseEntity<>(savedConsulta, HttpStatus.CREATED);
    }

    @PutMapping("/consultas/{id}")
    public ResponseEntity<ConsultaDTO> editConsulta(@PathVariable Long id, @RequestBody ConsultaDTO consultaDTO) {
        consultaService.getConsultaById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrada com o id: " + id));

        consultaDTO.setId(id);
        ConsultaDTO updatedConsulta = consultaService.updateConsulta(consultaDTO);
        return ResponseEntity.ok(updatedConsulta);
    }

    @DeleteMapping("/consultas/{id}")
    public ResponseEntity<Void> deleteConsulta(@PathVariable Long id) {
        if (consultaService.getConsultaById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrada com o id: " + id);
        }

        consultaService.deleteConsulta(id);
        return ResponseEntity.noContent().build();
    }
}
