package com.pet_love.demo.model.dto;

import java.util.List;

public class PessoaDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String cidade;
    private String telefone;
    private String email;
    private List<Long> petsIds;

    public PessoaDTO() {
    }

    public PessoaDTO(Long id, String nome, String cpf, String cidade, String telefone, String email, List<Long> petsIds) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cidade = cidade;
        this.telefone = telefone;
        this.email = email;
        this.petsIds = petsIds;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Long> getPetsIds() {
        return petsIds;
    }

    public void setPetsIds(List<Long> petsIds) {
        this.petsIds = petsIds;
    }

}
