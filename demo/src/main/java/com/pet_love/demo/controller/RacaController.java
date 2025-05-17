package com.pet_love.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pet_love.demo.model.dto.RacaDTO;
import com.pet_love.demo.service.RacaService;

@RestController
@RequestMapping("/api")
public class RacaController {

	@Autowired
	private RacaService racaService;
	
	@GetMapping("/racas")
	public List<RacaDTO>getAllRacas(){
		return racaService.getAllRacas();
	}
	
	@GetMapping("/racas/{id}")
	public ResponseEntity<RacaDTO>getRacaById(@PathVariable Long id){
		return racaService.getRacaById(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Raça não encontrada com ID: " + id));
	}
	
	@PostMapping("/racas")
	public ResponseEntity<RacaDTO>addRaca(@RequestBody RacaDTO racaDTO){
		RacaDTO savedRaca = racaService.saveRaca(racaDTO);
		return new ResponseEntity<>(savedRaca, HttpStatus.CREATED);
	}
	
	@PutMapping("/raca/{id}")
	public ResponseEntity<RacaDTO>editRaca(@PathVariable Long id, @RequestBody RacaDTO racaDTO){
		racaService.getRacaById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Raça não encontrada com ID: " + id));
		// Atualiza os dados
		racaDTO.setId(id);
		RacaDTO updateRaca = racaService.updateRaca(racaDTO);
		return ResponseEntity.ok(updateRaca);
	}
	
	@DeleteMapping("/racas/{id}")
	public ResponseEntity<Void>deleteRaca(@PathVariable Long id){
		if(racaService.getRacaById(id).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Raça não encontrada com ID: " + id);
		}
		racaService.deleteRaca(id);
		return ResponseEntity.noContent().build();
	}
	
}
