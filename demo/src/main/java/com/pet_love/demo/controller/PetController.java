package com.pet_love.demo.controller;

import com.pet_love.demo.model.Pessoa;
import com.pet_love.demo.model.Pet;
import com.pet_love.demo.model.dto.PessoaDTO;
import com.pet_love.demo.model.dto.PetDTO;
import com.pet_love.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping("/pets")
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("pets/{id}")
    public Optional<Pet> getPetById(@PathVariable Long id) {
        return petService.getPetById(id);
    }

    @GetMapping("pets/pessoas/{id}")
    public List<Pet> getPetsByIdPessoa(@PathVariable Long id) {
        return petService.buscarPetsPorPessoa(id);
    }

    @GetMapping("/pets/{id}/dono-principal")
    public ResponseEntity<PessoaDTO> getDonoPrincipal(@PathVariable Long id) {
        Optional<Pessoa> pessoaOpt = petService.buscarDonoPrincipal(id);

        return pessoaOpt
                .map(pessoa -> {
                    List<Long> petsIds = pessoa.getPets()
                            .stream()
                            .map(p -> p.getPet().getId())
                            .toList();

                    PessoaDTO dto = new PessoaDTO(
                            pessoa.getId(),
                            pessoa.getNome(),
                            pessoa.getCpf(),
                            pessoa.getCidade(),
                            pessoa.getTelefone(),
                            pessoa.getEmail(),
                            petsIds
                    );
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/pets")
    public ResponseEntity<Pet> addPet(@RequestBody PetDTO dto) {
        Pet petCriado = petService.salvarPetComDTO(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(petCriado);
    }

    @PutMapping("/pets/{id}")
    public ResponseEntity<Pet> editPet(@PathVariable Long id, @RequestBody PetDTO petDTO) {
        Pet oldPet = petService.getPetById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet não encontrado com ID: " + id));

        // Atualiza os dados
        petDTO.setId(oldPet.getId());
        Pet petAtualiado = petService.updatePet(petDTO); // Garante que o ID seja mantido
        return ResponseEntity.status(HttpStatus.CREATED).body(petAtualiado);
    }

    @DeleteMapping("pets/{id}")
    public void deletePet(@PathVariable Long id) {
        petService.deletePet(id);
    }

}
