package com.pet_love.demo.model.dto;

import java.time.LocalDateTime;

public class ConsultaDTO {
    private Long id;
    private LocalDateTime dataHora;
    private String observacoes;
    private double valor;
    private Long funcionarioId;
    private Long petId;

    public ConsultaDTO() {
    }

    public ConsultaDTO(Long id, LocalDateTime dataHora, String observacoes, double valor, Long funcionarioId, Long petId) {
        this.id = id;
        this.dataHora = dataHora;
        this.observacoes = observacoes;
        this.valor = valor;
        this.funcionarioId = funcionarioId;
        this.petId = petId;
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

    public Long getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Long funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }
}
