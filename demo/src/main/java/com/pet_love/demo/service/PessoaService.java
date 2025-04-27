package com.pet_love.demo.service;

import com.pet_love.demo.model.Pessoa;
import com.pet_love.demo.model.dto.PessoaDTO;
import com.pet_love.demo.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    /**
     * Método para converter um objeto da classe Pessoa para PessoaDTO
     * @param pessoa
     * @return
     */
    public static PessoaDTO convertToDTO(Pessoa pessoa) {
        List<Long> petsIds = pessoa.getPets().stream()
                .map(p -> p.getPet().getId())
                .collect(Collectors.toList());

        return new PessoaDTO(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getCidade(),
                pessoa.getTelefone(),
                pessoa.getEmail(),
                petsIds
        );
    }

    /**
     * Método para converter um objeto da classe PessoaDTO para Pessoa
     * @param pessoaDTO
     * @return
     */
    public static Pessoa convertFromDTO(PessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaDTO.getId());
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setCpf(pessoaDTO.getCpf());
        pessoa.setCidade(pessoaDTO.getCidade());
        pessoa.setTelefone(pessoaDTO.getTelefone());
        pessoa.setEmail(pessoaDTO.getEmail());
        return pessoa;
    }

    public List<PessoaDTO> getAllPessoas() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return pessoas.stream()
                .map(PessoaService::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<PessoaDTO> getPessoaById(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa.map(PessoaService::convertToDTO);
    }

    public PessoaDTO savePessoa(PessoaDTO pessoaDTO) {
        Pessoa savedPessoa = pessoaRepository.save(convertFromDTO(pessoaDTO));
        return convertToDTO(savedPessoa);
    }

    public PessoaDTO updatePessoa(PessoaDTO pessoaDTO) {
        Pessoa savedPessoa = pessoaRepository.save(convertFromDTO(pessoaDTO));
        return convertToDTO(savedPessoa);
    }

    public void deletePessoa(Long id) {
        pessoaRepository.deleteById(id);
    }
}
