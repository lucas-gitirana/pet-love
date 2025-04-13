package com.pet_love.demo.service;

import com.pet_love.demo.model.*;
import com.pet_love.demo.model.dto.PetDTO;
import com.pet_love.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EspecieRepository especieRepository;

    @Autowired
    private RacaRepository racaRepository;

    @Autowired
    private PessoaPetRepository pessoaPetRepository;

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Optional<Pet> getPetById(Long id) {
        return petRepository.findById(id);
    }

    public List<Pet> buscarPetsPorPessoa(Long pessoaId) {
        List<PessoaPet> pessoaPets = pessoaPetRepository.findByPessoaId(pessoaId);
        return pessoaPets.stream()
                .map(PessoaPet::getPet)
                .collect(Collectors.toList());
    }

    public Optional<Pessoa> buscarDonoPrincipal(Long petId) {
        return pessoaPetRepository.findByPetIdAndPrincipalTrue(petId)
                .map(PessoaPet::getPessoa);
    }

    public Pet salvarPetComDTO(PetDTO dto) {
        Pet pet = new Pet();
        pet.setNome(dto.getNome());
        pet.setDataNascimento(dto.getDataNascimento());
        pet.setObservacoes(dto.getObservacoes());
        pet.setFoto(dto.getFoto());

        // Buscar espécie e raça
        Especie especie = especieRepository.findById(dto.getEspecieId())
                .orElseThrow(() -> new RuntimeException("Espécie não encontrada"));
        pet.setEspecie(especie);

        if (dto.getRacaId() != null) {
            Raca raca = racaRepository.findById(dto.getRacaId())
                    .orElseThrow(() -> new RuntimeException("Raça não encontrada"));
            pet.setRaca(raca);
        }

        // Salvar pet primeiro para ter o ID
        Pet petSalvo = petRepository.save(pet);

        // Agora cria as novas ligações com os donos
        List<PessoaPet> donos = dto.getDonos().stream().map(p -> {
            Pessoa pessoa = pessoaRepository.findById(p.getPessoaId())
                    .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
            return new PessoaPet(pessoa, petSalvo, p.isPrincipal());
        }).toList();

        // Salva PessoaPet
        pessoaPetRepository.saveAll(donos);
        petSalvo.setDonos(donos);

        return petSalvo;
    }

    public Pet updatePet(PetDTO dto) {
        Pet pet = new Pet();
        pet.setId(dto.getId());
        pet.setNome(dto.getNome());
        pet.setDataNascimento(dto.getDataNascimento());
        pet.setObservacoes(dto.getObservacoes());
        pet.setFoto(dto.getFoto());

        // Buscar espécie e raça
        Especie especie = especieRepository.findById(dto.getEspecieId())
                .orElseThrow(() -> new RuntimeException("Espécie não encontrada"));
        pet.setEspecie(especie);

        if (dto.getRacaId() != null) {
            Raca raca = racaRepository.findById(dto.getRacaId())
                    .orElseThrow(() -> new RuntimeException("Raça não encontrada"));
            pet.setRaca(raca);
        }

        //Apagamos todas as relações entre pet e dono antes de atualizar
        for (PessoaPet pp : pet.getDonos()) {
            pessoaPetRepository.delete(pp);
        }

        // Salvar pet primeiro para ter o ID
        Pet petSalvo = petRepository.saveAndFlush(pet);

        // Agora cria as novas ligações com os donos
        List<PessoaPet> donos = dto.getDonos().stream().map(p -> {
            Pessoa pessoa = pessoaRepository.findById(p.getPessoaId())
                    .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
            return new PessoaPet(pessoa, petSalvo, p.isPrincipal());
        }).toList();

        // Salva PessoaPet
        pessoaPetRepository.saveAll(donos);
        petSalvo.setDonos(donos);

        return petSalvo;
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
}
