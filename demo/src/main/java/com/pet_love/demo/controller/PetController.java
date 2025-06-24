package com.pet_love.demo.controller;

import com.pet_love.demo.model.dto.PessoaDTO;
import com.pet_love.demo.model.dto.PetDTO;
import com.pet_love.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping("/pets")
    public List<PetDTO> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("pets/{id}")
    public ResponseEntity<PetDTO> getPetById(@PathVariable Long id) {
        return petService.getPetById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet não encontrado com ID: " + id));
    }

    @GetMapping("pets/pessoas/{id}")
    public List<PetDTO> getPetsByIdPessoa(@PathVariable Long id) {
        return petService.buscarPetsPorPessoa(id);
    }

    @GetMapping("/pets/{id}/dono-principal")
    public ResponseEntity<PessoaDTO> getDonoPrincipal(@PathVariable Long id) {
        return petService.buscarDonoPrincipal(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dono não encontrado com ID: " + id));
    }

    @GetMapping("/pets/sem-dono")
    public ResponseEntity<List<PetDTO>> listarPetsSemDono() {
        List<PetDTO> pets = petService.buscarPetsSemDono();
        return ResponseEntity.ok(pets);
    }

    @PostMapping("/pets")
    public ResponseEntity<PetDTO> addPet(@RequestBody PetDTO dto) {
        PetDTO savedPet = petService.savePet(dto);
        return new ResponseEntity<>(savedPet, HttpStatus.CREATED);
    }

    @PutMapping("/pets/{id}")
    public ResponseEntity<PetDTO> editPet(@PathVariable Long id, @RequestBody PetDTO petDTO) {
        petService.getPetById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet não encontrado com ID: " + id));

        // Atualiza os dados
        petDTO.setId(id);
        PetDTO updatedPet = petService.updatePet(petDTO);
        return ResponseEntity.ok(updatedPet);
    }

    @DeleteMapping("pets/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }

}
