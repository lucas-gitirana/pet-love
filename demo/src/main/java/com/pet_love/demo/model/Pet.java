package com.pet_love.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pet_id")
    private Long id;

    @Column(name="pet_nome", nullable = false)
    private String nome;

    @Column(name="pet_data_nasc", nullable = false)
    private String dataNascimento;

    @Column(name="pet_observacoes")
    private String observacoes;

    @Column(name="pet_foto")
    private String foto;

    @ManyToOne
    @JoinColumn(name="esp_id", nullable = false)
    private Especie especie;

    @ManyToOne
    @JoinColumn(name="rac_id")
    private Raca raca;

    @OneToMany(mappedBy = "pet")
    private List<PessoaPet> donos = new ArrayList<>();

    public Pet() {
    }

    public Pet(Long id, String nome, String dataNasc, String observacoes, String foto, Especie especie, Raca raca, List<PessoaPet> donos) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNasc;
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

    public List<PessoaPet> getDonos() {
        return donos;
    }

    public void setDonos(List<PessoaPet> donos) {
        this.donos = donos;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataNasc='" + dataNascimento + '\'' +
                ", observacoes='" + observacoes + '\'' +
                ", foto='" + foto + '\'' +
                ", especie=" + especie +
                ", raca=" + raca +
                ", donos=" + donos +
                '}';
    }
}
