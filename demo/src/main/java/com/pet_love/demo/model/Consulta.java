package com.pet_love.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="con_id")
    private Long id;

    @Column(name="con_data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(name="con_observacoes")
    private String observacoes;

    @Column(name="con_valor", nullable = false)
    private double valor;

    @ManyToOne
    @JoinColumn(name="pes_id", nullable = false)
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name="pet_id", nullable = false)
    private Pet pet;

    public Consulta() {
    }

    public Consulta(Long id, LocalDateTime dataHora, String observacoes, double valor, Funcionario funcionario, Pet pet) {
        this.id = id;
        this.dataHora = dataHora;
        this.observacoes = observacoes;
        this.valor = valor;
        this.funcionario = funcionario;
        this.pet = pet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String  toString() {
        return "Consulta{" +
                "id=" + id +
                ", dataHora=" + dataHora +
                ", observacoes='" + observacoes + '\'' +
                ", valor=" + valor +
                ", funcionario=" + funcionario +
                ", pet=" + pet +
                '}';
    }
}
