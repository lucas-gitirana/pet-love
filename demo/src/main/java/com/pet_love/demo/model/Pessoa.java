package com.pet_love.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pes_id")
    private Long id;

    @Column(name="pes_nome", nullable = false)
    private String nome;

    @Column(name="pes_cpf", nullable = false)
    private String cpf;

    @Column(name="pes_cidade")
    private String cidade;

    @Column(name="pes_telefone")
    private String telefone;

    @Column(name="pes_email", nullable = false)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "pessoa")
    private List<PessoaPet> pets = new ArrayList<>();

    public Pessoa() {
    }

    public Pessoa(Long id, String nome, String cpf, String cidade, String telefone, String email, List<PessoaPet> pets) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cidade = cidade;
        this.telefone = telefone;
        this.email = email;
        this.pets = pets;
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

    public List<PessoaPet> getPets() {
        return pets;
    }

    public void setPets(List<PessoaPet> pets) {
        this.pets = pets;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", cidade='" + cidade + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", pets=" + pets +
                '}';
    }
}
