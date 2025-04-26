package com.pet_love.demo.service;

import com.pet_love.demo.model.Pessoa;
import com.pet_love.demo.model.PessoaPet;
import com.pet_love.demo.model.Pet;
import com.pet_love.demo.model.dto.PessoaPetDTO;
import com.pet_love.demo.model.dto.PetDTO;
import com.pet_love.demo.repository.PessoaPetRepository;
import com.pet_love.demo.repository.PessoaRepository;
import com.pet_love.demo.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaPetService {

    @Autowired
    private PessoaPetRepository pessoaPetRepository;

    private static PessoaRepository pessoaRepository;
    private static PetRepository petRepository;

    /**
     * Método para converter um objeto da classe PessoaPet para PessoaPetDTO
     * @param pessoaPet
     * @return
     */
    public static PessoaPetDTO convertToDTO(PessoaPet pessoaPet) {
        return new PessoaPetDTO(
                pessoaPet.getPessoa().getId(),
                pessoaPet.getPet().getId(),
                pessoaPet.isPrincipal()
        );
    }

    /**
     * Método para converter um objeto da classe PessoaPetDTO para PessoaPet
     * @param pessoaDTO
     * @return
     */
    public static PessoaPet convertFromDTO(PessoaPetDTO pessoaDTO) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(pessoaDTO.getPessoaId());
        Optional<Pet> pet = petRepository.findById(pessoaDTO.getPetId());

        PessoaPet pessoaPet = new PessoaPet();
        pessoa.ifPresent(pessoaPet::setPessoa);
        pet.ifPresent(pessoaPet::setPet);
        pessoaPet.setPrincipal(pessoaDTO.isPrincipal());
        return pessoaPet;
    }
}
