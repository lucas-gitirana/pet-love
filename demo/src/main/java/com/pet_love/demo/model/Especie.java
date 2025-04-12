package com.pet_love.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "especie")
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="esp_id")
    private Long id;

    @Column(name="esp_nome", nullable = false)
    private String nome;

    public Especie() {
    }

    public Especie(Long id, String nome) {
        this.id = id;
        this.nome = nome;
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

    @Override
    public String toString() {
        return "Especie{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
