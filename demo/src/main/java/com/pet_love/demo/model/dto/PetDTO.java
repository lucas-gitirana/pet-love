package com.pet_love.demo.model.dto;

import com.pet_love.demo.model.Especie;
import com.pet_love.demo.model.Raca;

import java.util.List;

public class PetDTO {
    private Long id;
    private String nome;
    private String dataNascimento;
    private String observacoes;
    private String foto;
    private List<PessoaPetDTO> donos;
    private Especie especie;
    private Raca raca;

    public PetDTO() {
    }

    public PetDTO(Long id, String nome, String dataNascimento, String observacoes, String foto, Especie especie, Raca raca, List<PessoaPetDTO> donos) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.observacoes = observacoes;
        this.foto = foto;
        this.especie = especie;
        this.raca = raca;
        this.donos = donos;
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<PessoaPetDTO> getDonos() {
        return donos;
    }

    public void setDonos(List<PessoaPetDTO> donos) {
        this.donos = donos;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    @Override
    public String toString() {
        return "PetDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", observacoes='" + observacoes + '\'' +
                ", foto='" + foto + '\'' +
                ", donos=" + donos +
                ", especie=" + especie +
                ", raca=" + raca +
                '}';
    }
}
