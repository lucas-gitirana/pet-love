package com.pet_love.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "raca")
public class Raca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rac_id")
    private Long id;

    @Column(name="rac_nome", nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "esp_id")
    private Especie especie;

    public Raca() {
    }

    public Raca(Long id, String nome, Especie especie) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
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

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    @Override
    public String toString() {
        return "Raca{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", especie=" + especie +
                '}';
    }
}

