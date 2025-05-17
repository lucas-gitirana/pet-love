package com.pet_love.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="usu_id")
    private Long id;

    @Column(name="usu_login", nullable = false)
    private String login;

    @Column(name="usu_senha", nullable = false)
    @JsonIgnore
    private String senha;

    @Column(name="usu_perfil", nullable = false)
    private int perfil;

    @OneToOne
    @JoinColumn(name = "pessoa_pes_id", nullable = false)
    private Pessoa pessoa;

    public Usuario() {
    }

    public Usuario(Long id, String login, String senha, int perfil, Pessoa pessoa) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
        this.pessoa = pessoa;
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", perfil=" + perfil +
                ", pessoa=" + pessoa +
                '}';
    }
}
