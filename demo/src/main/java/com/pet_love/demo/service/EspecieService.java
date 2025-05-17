package com.pet_love.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet_love.demo.model.Especie;
import com.pet_love.demo.model.dto.EspecieDTO;
import com.pet_love.demo.repository.EspecieRepository;

@Service
public class EspecieService {
	
	@Autowired
	private EspecieRepository especieRepository;
	
	/**
	 * Método para converter um objeto da classe Especie para EspecieDTO
	 * @param especie
	 * @return
	 */
	
	public static EspecieDTO convertToDTO(Especie especie) {
		return new EspecieDTO(
				especie.getId(),
				especie.getNome()
		);
	}
	
	/**
	 * Método para converter um objeto da classe EspecieDTO para Especie
	 * @param especieDTO
	 * @return
	 */
	
	public static Especie convertFromDTO(EspecieDTO especieDTO) {
		Especie especie = new Especie();
		especie.setId(especieDTO.getId());
		especie.setNome(especieDTO.getNome());
		return especie;
	}
	
	public List<EspecieDTO> getAllEspecies(){
		List<Especie> especies = especieRepository.findAll();
		return especies.stream()
				.map(EspecieService::convertToDTO)
				.collect(Collectors.toList());
	}
	
	public Optional<EspecieDTO> getEspecieById(Long id){
		Optional<Especie> especie = especieRepository.findById(id);
		return especie.map(EspecieService::convertToDTO);
	}
	
	public EspecieDTO saveEspecie(EspecieDTO especieDTO) {
		Especie savedEspecie = especieRepository.save(convertFromDTO(especieDTO));
		return convertToDTO(savedEspecie);
	}
	
	public EspecieDTO updateEspecie(EspecieDTO especieDTO) {
		Especie savedEspecie = especieRepository.save(convertFromDTO(especieDTO));
		return convertToDTO(savedEspecie);
	}
	
	public void deleteEspecie(Long id) {
		especieRepository.deleteById(id);;
	}
}
