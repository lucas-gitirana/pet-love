package com.pet_love.demo.model.dto;

import com.pet_love.demo.model.Especie;

public class RacaDTO {
	private Long id;
	private String nome;
	private Especie especie;
	
	public RacaDTO() {
	}

	public RacaDTO(Long id, String nome, Especie especie) {
		this.id = id;
		this.nome = nome;
		this.especie = especie;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	@Override
	public String toString() {
		return "RacaDTO {" + 
				"id=" + id + 
				", nome=" + nome + 
				", especie=" + especie + '}';
	}
	
	
}
