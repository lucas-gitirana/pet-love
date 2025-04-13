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

    // Método para converter Pessoa em PessoaDTO
    private PessoaDTO convertToDTO(Pessoa pessoa) {
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

    public List<PessoaDTO> getAllPessoas() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return pessoas.stream()
                .map(this::convertToDTO)  // Converter para DTO
                .collect(Collectors.toList());
    }

    public Optional<PessoaDTO> getPessoaById(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa.map(this::convertToDTO);  // Converter para DTO
    }

    public PessoaDTO savePessoa(PessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setCpf(pessoaDTO.getCpf());
        pessoa.setCidade(pessoaDTO.getCidade());
        pessoa.setTelefone(pessoaDTO.getTelefone());
        pessoa.setEmail(pessoaDTO.getEmail());

        Pessoa savedPessoa = pessoaRepository.save(pessoa);
        return convertToDTO(savedPessoa);  // Retorna o DTO da pessoa salva
    }

    public PessoaDTO updatePessoa(PessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaDTO.getId());
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setCpf(pessoaDTO.getCpf());
        pessoa.setCidade(pessoaDTO.getCidade());
        pessoa.setTelefone(pessoaDTO.getTelefone());
        pessoa.setEmail(pessoaDTO.getEmail());

        Pessoa savedPessoa = pessoaRepository.save(pessoa);
        return convertToDTO(savedPessoa);  // Retorna o DTO da pessoa salva
    }

    public void deletePessoa(Long id) {
        pessoaRepository.deleteById(id);
    }
}
