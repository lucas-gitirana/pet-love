package com.pet_love.demo.model.dto;

public class SolicitacaoAdocaoDTO {

    private Long id;
    private String dataHora;
    private int status;
    private Long pessoaId;
    private Long petId;

    public SolicitacaoAdocaoDTO() {
    }

    public SolicitacaoAdocaoDTO(Long id, String dataHora, int status, Long pessoaId, Long petId) {
        this.id = id;
        this.dataHora = dataHora;
        this.status = status;
        this.pessoaId = pessoaId;
        this.petId = petId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    @Override
    public String toString() {
        return "SolicitacaoAdocaoDTO{" +
                "id=" + id +
                ", dataHora='" + dataHora + '\'' +
                ", status=" + status +
                ", pessoaId=" + pessoaId +
                ", petId=" + petId +
                '}';
    }
}
