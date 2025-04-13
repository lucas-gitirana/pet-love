package com.pet_love.demo.service;

import com.pet_love.demo.model.Funcionario;
import com.pet_love.demo.model.dto.FuncionarioDTO;
import com.pet_love.demo.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<FuncionarioDTO> listarTodos() {
        return funcionarioRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public FuncionarioDTO buscarPorId(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));
        return toDTO(funcionario);
    }

    public FuncionarioDTO criar(FuncionarioDTO dto) {
        Funcionario funcionario = toEntity(dto);
        return toDTO(funcionarioRepository.save(funcionario));
    }

    public FuncionarioDTO atualizar(Long id, FuncionarioDTO dto) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));

        funcionario.setNome(dto.getNome());
        funcionario.setCpf(dto.getCpf());
        funcionario.setCidade(dto.getCidade());
        funcionario.setTelefone(dto.getTelefone());
        funcionario.setEmail(dto.getEmail());
        funcionario.setCrmv(dto.getCrmv());
        funcionario.setFuncao(dto.getFuncao());

        return toDTO(funcionarioRepository.save(funcionario));
    }

    public void deletar(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Funcionário não encontrado");
        }
        funcionarioRepository.deleteById(id);
    }

    // Conversões
    private FuncionarioDTO toDTO(Funcionario f) {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setId(f.getId());
        dto.setNome(f.getNome());
        dto.setCpf(f.getCpf());
        dto.setCidade(f.getCidade());
        dto.setTelefone(f.getTelefone());
        dto.setEmail(f.getEmail());
        dto.setCrmv(f.getCrmv());
        dto.setFuncao(f.getFuncao());
        return dto;
    }

    private Funcionario toEntity(FuncionarioDTO dto) {
        Funcionario f = new Funcionario();
        f.setId(dto.getId());
        f.setNome(dto.getNome());
        f.setCpf(dto.getCpf());
        f.setCidade(dto.getCidade());
        f.setTelefone(dto.getTelefone());
        f.setEmail(dto.getEmail());
        f.setCrmv(dto.getCrmv());
        f.setFuncao(dto.getFuncao());
        return f;
    }
}
