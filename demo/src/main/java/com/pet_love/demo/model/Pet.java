package com.pet_love.demo.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "per_id")
    private Person dono;

    @Column(name = "pet_nome")
    private String nome;

    @Column(name = "pet_especie")
    private String especie;

    @Column(name = "pet_raca")
    private String raca;

    @Column(name = "pet_data_nasc")
    private Date dataNascimento;

    @Column(name = "pet_observacoes")
    private String observacoes;

    @Column(name = "pet_foto_path")
    private String fotoPath;

    public Pet() {
    }

    public Pet(Long id, Person dono, String nome, String especie, String raca, Date dataNascimento, String observacoes, String fotoPath) {
        this.id = id;
        this.dono = dono;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.dataNascimento = dataNascimento;
        this.observacoes = observacoes;
        this.fotoPath = fotoPath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getDono() {
        return dono;
    }

    public void setDono(Person dono) {
        this.dono = dono;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getFotoPath() {
        return fotoPath;
    }

    public void setFotoPath(String fotoPath) {
        this.fotoPath = fotoPath;
    }
}
