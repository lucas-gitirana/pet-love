package com.pet_love.demo.service;

import com.pet_love.demo.model.Consulta;
import com.pet_love.demo.model.Funcionario;
import com.pet_love.demo.model.Pet;
import com.pet_love.demo.model.dto.ConsultaDTO;
import com.pet_love.demo.repository.ConsultaRepository;
import com.pet_love.demo.repository.FuncionarioRepository;
import com.pet_love.demo.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private PetRepository petRepository;

    /**
     * Método para converter um objeto da classe Consulta para ConsultaDTO
     * @param consulta
     * @return
     */
    public static ConsultaDTO convertToDTO(Consulta consulta) {
        return new ConsultaDTO(
          consulta.getId(),
          consulta.getDataHora(),
          consulta.getObservacoes(),
          consulta.getValor(),
          consulta.getFuncionario().getId(),
          consulta.getPet().getId()
        );
    }

    /**
     * Método para converter um objeto da classe ConsultaDTO para Consulta
     * @param consultaDTO
     * @return
     */
    public Consulta convertFromDTO(ConsultaDTO consultaDTO) {
        Funcionario funcionario = funcionarioRepository.findById(consultaDTO.getFuncionarioId())
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com o id: " + consultaDTO.getFuncionarioId()));

        Pet pet = petRepository.findById(consultaDTO.getPetId())
                .orElseThrow(() -> new EntityNotFoundException("Pet não encontrado com o id: " + consultaDTO.getPetId()));

        Consulta consulta = new Consulta();
        consulta.setId(consultaDTO.getId());
        consulta.setDataHora(consultaDTO.getDataHora());
        consulta.setObservacoes(consultaDTO.getObservacoes());
        consulta.setValor(consultaDTO.getValor());
        consulta.setFuncionario(funcionario);
        consulta.setPet(pet);
        return  consulta;
    }

    public List<ConsultaDTO> getAllConsultas() {
        List<Consulta> consultas = consultaRepository.findAll();
        return consultas.stream()
                .map(ConsultaService::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ConsultaDTO> getConsultaById(Long id) {
        Optional<Consulta> consulta = consultaRepository.findById(id);
        return consulta.map(ConsultaService::convertToDTO);
    }

    public ConsultaDTO saveConsulta(ConsultaDTO consultaDTO) {
        Consulta savedConsulta = consultaRepository.save(convertFromDTO(consultaDTO));
        return convertToDTO(savedConsulta);
    }

    public ConsultaDTO updateConsulta(ConsultaDTO consultaDTO) {
        Consulta savedConsulta = consultaRepository.save(convertFromDTO(consultaDTO));
        return convertToDTO(savedConsulta);
    }

    public void deleteConsulta(Long id) {
        consultaRepository.deleteById(id);
    }
}
