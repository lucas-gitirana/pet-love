package com.pet_love.demo.model.dto;

import java.util.List;

public class PetDTO {
    private String nome;
    private String dataNascimento;
    private String observacoes;
    private String foto;
    private Long especieId;
    private Long racaId;
    private List<PessoaPetDTO> donos;

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

    public Long getEspecieId() {
        return especieId;
    }

    public void setEspecieId(Long especieId) {
        this.especieId = especieId;
    }

    public Long getRacaId() {
        return racaId;
    }

    public void setRacaId(Long racaId) {
        this.racaId = racaId;
    }

    public List<PessoaPetDTO> getDonos() {
        return donos;
    }

    public void setDonos(List<PessoaPetDTO> donos) {
        this.donos = donos;
    }
}
