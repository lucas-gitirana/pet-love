package com.pet_love.demo.service;

import com.pet_love.demo.model.*;
import com.pet_love.demo.model.dto.SolicitacaoAdocaoDTO;
import com.pet_love.demo.repository.PessoaRepository;
import com.pet_love.demo.repository.PetRepository;
import com.pet_love.demo.repository.SolicitacaoAdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SolicitacaoAdocaoService {

    @Autowired
    SolicitacaoAdocaoRepository solicitacaoRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    /**
     * Método para converter um objeto da classe SolicitacaoAdocao para SolicitacaoAdocaoDTO
     * @param solicitacao
     * @return
     */
    public static SolicitacaoAdocaoDTO convertToDTO(SolicitacaoAdocao solicitacao) {
        return new SolicitacaoAdocaoDTO(
                solicitacao.getId(),
                solicitacao.getDataHora(),
                solicitacao.getStatus(),
                solicitacao.getPessoa().getId(),
                solicitacao.getPet().getId()
        );
    }

    /**
     * Método para converter um objeto da classe SolicitacaoAdocaoDTO para SolicitacaoAdocao
     * @param solicitacaoDTO
     * @param pessoaRepository
     * @param petRepository
     * @return
     */
    public static SolicitacaoAdocao convertFromDTO(
            SolicitacaoAdocaoDTO solicitacaoDTO,
            PessoaRepository pessoaRepository,
            PetRepository petRepository
    ) {

        SolicitacaoAdocao solicitacao = new SolicitacaoAdocao();

        Optional<Pessoa> pessoa = pessoaRepository.findById(solicitacaoDTO.getPessoaId());
        pessoa.ifPresent(solicitacao::setPessoa);

        Optional<Pet> pet = petRepository.findById(solicitacaoDTO.getPetId());
        pet.ifPresent(solicitacao::setPet);

        solicitacao.setId(solicitacaoDTO.getId());
        solicitacao.setStatus(solicitacaoDTO.getStatus());
        solicitacao.setDataHora(solicitacaoDTO.getDataHora());
        return solicitacao;
    }

    public List<SolicitacaoAdocaoDTO> getAllSolicitacoes() {
        List<SolicitacaoAdocao> solicitacoes = solicitacaoRepository.findAll();
        return solicitacoes.stream()
                .map(SolicitacaoAdocaoService::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<SolicitacaoAdocaoDTO> getSolicitacaoAdocaoById(Long id) {
        Optional<SolicitacaoAdocao> pet = solicitacaoRepository.findById(id);
        return pet.map(SolicitacaoAdocaoService::convertToDTO);
    }

    public SolicitacaoAdocaoDTO saveSolicitacaoAdocao(SolicitacaoAdocaoDTO solicitacaoDTO) {
        SolicitacaoAdocao savedSolicitacao = solicitacaoRepository.save(convertFromDTO(
                solicitacaoDTO,
                pessoaRepository,
                petRepository));

        return convertToDTO(savedSolicitacao);
    }

    public SolicitacaoAdocaoDTO updateSolicitacaoAdocao(SolicitacaoAdocaoDTO solicitacaoDTO) {
        SolicitacaoAdocao savedSolicitacao = solicitacaoRepository.save(convertFromDTO(
                solicitacaoDTO,
                pessoaRepository,
                petRepository));

        return convertToDTO(savedSolicitacao);
    }

    public void deleteSolicitacaoAdocao(Long id) {
        solicitacaoRepository.deleteById(id);
    }
}
