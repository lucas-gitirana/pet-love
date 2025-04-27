package com.pet_love.demo.service;

import com.pet_love.demo.model.Funcionario;
import com.pet_love.demo.model.dto.FuncionarioDTO;
import com.pet_love.demo.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    /**
     * Método para converter um objeto da classe Funcionario para FuncionarioDTO
     * @param funcionario
     * @return
     */
    public static FuncionarioDTO convertToDTO(Funcionario funcionario) {
        return new FuncionarioDTO(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getCidade(),
                funcionario.getTelefone(),
                funcionario.getEmail(),
                funcionario.getCrmv(),
                funcionario.getFuncao()
        );
    }

    /**
     * Método para converter um objeto da classe FuncionarioDTO para Funcionario
     * @param funcionarioDTO
     * @return
     */
    public static Funcionario convertFromDTO(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(funcionarioDTO.getId());
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setCpf(funcionarioDTO.getCpf());
        funcionario.setCidade(funcionarioDTO.getCidade());
        funcionario.setTelefone(funcionarioDTO.getTelefone());
        funcionario.setEmail(funcionarioDTO.getEmail());
        funcionario.setCrmv(funcionarioDTO.getCrmv());
        funcionario.setFuncao(funcionarioDTO.getFuncao());
        return funcionario;
    }

    public List<FuncionarioDTO> getAllFuncionarios() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream()
                .map(FuncionarioService::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<FuncionarioDTO> getFuncionarioById(Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return funcionario.map(FuncionarioService::convertToDTO);
    }

    public FuncionarioDTO saveFuncionario(FuncionarioDTO funcionarioDTO) {
        Funcionario savedFuncionario = funcionarioRepository.save(convertFromDTO(funcionarioDTO));
        return convertToDTO(savedFuncionario);
    }

    public FuncionarioDTO updateFuncionario(FuncionarioDTO funcionarioDTO) {
        Funcionario savedFuncionario = funcionarioRepository.save(convertFromDTO(funcionarioDTO));
        return convertToDTO(savedFuncionario);
    }

    public void deleteFuncionario(Long id) {
        funcionarioRepository.deleteById(id);
    }
}
