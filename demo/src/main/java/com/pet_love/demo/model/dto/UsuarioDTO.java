package com.pet_love.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UsuarioDTO {
    private Long id;
    private String login;
    private String senha;
    private int perfil;
    private Long pessoaId;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String login, String senha, int perfil, Long pessoaId) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
        this.pessoaId = pessoaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }
}
