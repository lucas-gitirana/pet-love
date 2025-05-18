package com.pet_love.demo.model;

import jakarta.persistence.*;

@Entity(name = "solicitacao_adocao")
public class SolicitacaoAdocao {

    @Id
    @GeneratedValue
    @Column(name = "sad_id")
    private Long id;

    @Column(name = "sad_data_hora")
    private String dataHora;

    @Column(name = "sad_status")
    private int status;

    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    @ManyToOne(targetEntity = Pet.class)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public SolicitacaoAdocao() {
    }

    public SolicitacaoAdocao(Long id, String dataHora, int status, Pessoa pessoa, Pet pet) {
        this.id = id;
        this.dataHora = dataHora;
        this.status = status;
        this.pessoa = pessoa;
        this.pet = pet;
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "SolicitacaoAdocao{" +
                "id=" + id +
                ", dataHora='" + dataHora + '\'' +
                ", status=" + status +
                ", pessoa=" + pessoa +
                ", pet=" + pet +
                '}';
    }
}
