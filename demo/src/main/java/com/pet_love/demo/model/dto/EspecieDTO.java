package com.pet_love.demo.model.dto;

public class EspecieDTO {
	private Long id;
	private String nome;
	
	public EspecieDTO() {
	}

	public EspecieDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
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

	@Override
	public String toString() {
		return "EspecieDTO{" + 
				"id=" + id + 
				", nome=" + nome + '}';
	}
	
	
}
