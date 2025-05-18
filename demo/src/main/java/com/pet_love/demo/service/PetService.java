package com.pet_love.demo.service;

import com.pet_love.demo.model.*;
import com.pet_love.demo.model.dto.PessoaDTO;
import com.pet_love.demo.model.dto.PessoaPetDTO;
import com.pet_love.demo.model.dto.PetDTO;
import com.pet_love.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    /**
     * Método para converter um objeto da classe Pet para PetDTO
     * @param pet
     * @return
     */
    public static PetDTO convertToDTO(Pet pet) {
        List<PessoaPetDTO> donosDTO = pet.getDonos().stream()
                .map(PessoaPetService::convertToDTO)
                .collect(Collectors.toList());

        return new PetDTO(
                pet.getId(),
                pet.getNome(),
                pet.getDataNascimento(),
                pet.getObservacoes(),
                pet.getFoto(),
                pet.getEspecie(),
                pet.getRaca(),
                donosDTO
        );
    }

    /**
     * Método para converter um objeto da classe PessoaDTO para Pessoa
     * @param petDTO
     * @return
     */
    public static Pet convertFromDTO(PetDTO petDTO) {
        Pet pet = new Pet();
        pet.setId(petDTO.getId());
        pet.setNome(petDTO.getNome());
        pet.setDataNascimento(petDTO.getDataNascimento());
        pet.setFoto(petDTO.getFoto());
        pet.setObservacoes(petDTO.getObservacoes());
        pet.setRaca(petDTO.getRaca());
        pet.setEspecie(petDTO.getEspecie());
        return pet;
    }

    public List<PetDTO> getAllPets() {
        List<Pet> pets = petRepository.findAll();
        return pets.stream()
                .map(PetService::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<PetDTO> getPetById(Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        return pet.map(PetService::convertToDTO);
    }

    public List<PetDTO> buscarPetsPorPessoa(Long pessoaId) {
        List<PessoaPet> pessoaPets = pessoaPetRepository.findByPessoaId(pessoaId);
        return pessoaPets.stream()
                .map(p -> convertToDTO(p.getPet()))
                .collect(Collectors.toList());
    }

    public Optional<PessoaDTO> buscarDonoPrincipal(Long petId) {
        return pessoaPetRepository.findByPetIdAndPrincipalTrue(petId)
                .map(pessoaPet -> PessoaService.convertToDTO(pessoaPet.getPessoa()));
    }

    public PetDTO savePet(PetDTO petDTO) {
        Optional<Especie> especie = especieRepository.findById(petDTO.getEspecie().getId());
        especie.ifPresent(petDTO::setEspecie);

        Optional<Raca> raca = racaRepository.findById(petDTO.getRaca().getId());
        raca.ifPresent(petDTO::setRaca);

        Pet savedPet = petRepository.save(convertFromDTO(petDTO));
        petRepository.flush();

        List<PessoaPet> donos = new ArrayList<>();
        for (PessoaPetDTO pp : petDTO.getDonos()) {
            Optional<Pessoa> pessoa = pessoaRepository.findById(pp.getPessoaId());
            if (pessoa.isPresent()) {
                PessoaPet pessoaPet = new PessoaPet();
                pessoaPet.setPessoa(pessoa.get());
                pessoaPet.setPet(savedPet);
                pessoaPet.setPrincipal(pp.isPrincipal());
                donos.add(pessoaPet);
            }
        }

        // Salvar os novos donos
        pessoaPetRepository.saveAll(donos);

        // Atualizar no objeto em memória também
        savedPet.setDonos(donos);

        return convertToDTO(savedPet);
    }

    public PetDTO updatePet(PetDTO petDTO) {
        Optional<Especie> especie = especieRepository.findById(petDTO.getEspecie().getId());
        especie.ifPresent(petDTO::setEspecie);

        Optional<Raca> raca = racaRepository.findById(petDTO.getRaca().getId());
        raca.ifPresent(petDTO::setRaca);

        Pet savedPet = petRepository.save(convertFromDTO(petDTO));
        petRepository.flush();

        // Limpar os donos antigos no banco
        pessoaPetRepository.deleteByPetId(savedPet.getId());

        List<PessoaPet> donos = new ArrayList<>();
        for (PessoaPetDTO pp : petDTO.getDonos()) {
            Optional<Pessoa> pessoa = pessoaRepository.findById(pp.getPessoaId());
            if (pessoa.isPresent()) {
                PessoaPet pessoaPet = new PessoaPet();
                pessoaPet.setPessoa(pessoa.get());
                pessoaPet.setPet(savedPet);
                pessoaPet.setPrincipal(pp.isPrincipal());
                donos.add(pessoaPet);
            }
        }

        // Salvar os novos donos
        pessoaPetRepository.saveAll(donos);

        // Atualizar no objeto em memória também
        savedPet.setDonos(donos);

        return convertToDTO(savedPet);
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
}
