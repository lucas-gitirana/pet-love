package com.pet_love.demo.model.dto;

public class UsuarioResponseDTO {
    private Long id;
    private String login;
    private int perfil;
    private Long pessoaId;

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(Long id, String login, int perfil, Long pessoaId) {
        this.id = id;
        this.login = login;
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
