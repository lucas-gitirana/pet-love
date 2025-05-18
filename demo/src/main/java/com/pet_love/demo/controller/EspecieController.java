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

import com.pet_love.demo.model.dto.EspecieDTO;
import com.pet_love.demo.service.EspecieService;

@RestController
@RequestMapping("/api")
public class EspecieController {
	
	@Autowired
	private EspecieService especieService;
	
	@GetMapping("/especies")
	public List<EspecieDTO> getALLEspecies(){
		return especieService.getAllEspecies();
	}
	
	@GetMapping("/especies/{id}")
	public ResponseEntity<EspecieDTO>getEspecieById(@PathVariable Long id){
		return especieService.getEspecieById(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Espécie não encontrada com ID: " + id));
	}
	
	@PostMapping("/especies")
	public ResponseEntity<EspecieDTO>addEspecie(@RequestBody EspecieDTO especieDTO){
		EspecieDTO savedEspecie = especieService.saveEspecie(especieDTO);
		return new ResponseEntity<>(savedEspecie, HttpStatus.CREATED);
	}
	
	@PutMapping("/especies/{id}")
	public ResponseEntity<EspecieDTO> editEspecie(@PathVariable Long id, @RequestBody EspecieDTO especieDTO){
		especieService.getEspecieById(id)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Espécie não encontrada com ID: " + id));
		
		// Atualiza os dados
		especieDTO.setId(id);
		EspecieDTO updateEspecie = especieService.updateEspecie(especieDTO);
		return ResponseEntity.ok(updateEspecie);
	}
	
	@DeleteMapping("/especies/{id}")
	public ResponseEntity<Void>deleteEspecie(@PathVariable Long id){
		if(especieService.getEspecieById(id).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Espécie não encontrada com ID: " + id);
		}
		especieService.deleteEspecie(id);
		return ResponseEntity.noContent().build();
	}
}
