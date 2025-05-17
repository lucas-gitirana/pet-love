package com.pet_love.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet_love.demo.model.Especie;
import com.pet_love.demo.model.Raca;
import com.pet_love.demo.model.dto.RacaDTO;
import com.pet_love.demo.repository.EspecieRepository;
import com.pet_love.demo.repository.RacaRepository;

@Service
public class RacaService {
	
	@Autowired
	private RacaRepository racaRepository;
	
	@Autowired
	private EspecieRepository especieRepository;
	
	/**
	 * Método para converter um objeto da classe Raca para RacaDTO
	 * @param raca
	 * @return
	 */
	
	public static RacaDTO convertToDTO(Raca raca) {
		return new RacaDTO(
				raca.getId(),
				raca.getNome(),
				raca.getEspecie()
		);
	}
	
	/**
	 * Método para converter um objeto da classe RacaDTO para Raca
	 * @param racaDTO
	 * @return
	 */
	
	public static Raca convertFromDTO(RacaDTO racaDTO) {
		Raca raca = new Raca();
		raca.setId(racaDTO.getId());
		raca.setNome(racaDTO.getNome());
		raca.setEspecie(racaDTO.getEspecie());
		return raca;
	}
	
	public List<RacaDTO>getAllRacas(){
		List<Raca>racas = racaRepository.findAll();
		return racas.stream()
				.map(RacaService::convertToDTO)
				.collect(Collectors.toList());
	}
	
	public Optional<RacaDTO>getRacaById(Long id){
		Optional<Raca>raca = racaRepository.findById(id);
		return raca.map(RacaService::convertToDTO);
	}
	
	public RacaDTO saveRaca(RacaDTO racaDTO) {
		Optional<Especie>especie = especieRepository.findById(racaDTO.getEspecie().getId());
		especie.ifPresent(racaDTO::setEspecie);
		
		Raca savedRaca = racaRepository.save(convertFromDTO(racaDTO));
		return convertToDTO(savedRaca);
	}
	
	public RacaDTO updateRaca(RacaDTO racaDTO) {
		Optional<Especie>especie = especieRepository.findById(racaDTO.getEspecie().getId());
		especie.ifPresent(racaDTO::setEspecie);
		
		Raca savedRaca = racaRepository.save(convertFromDTO(racaDTO));
		return convertToDTO(savedRaca);
	}
	
	public void deleteRaca(Long id) {
		racaRepository.deleteById(id);
	}
}
