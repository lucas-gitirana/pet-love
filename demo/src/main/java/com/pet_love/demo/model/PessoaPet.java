package com.pet_love.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "pes_pet")
@IdClass(PessoaPetId.class)
public class PessoaPet {

    @Id
    @ManyToOne()
    @JoinColumn(name="pes_id", nullable = false)
    private Pessoa pessoa;

    @Id
    @ManyToOne()
    @JoinColumn(name="pet_id", nullable = false)
    @JsonIgnore
    @JsonBackReference
    private Pet pet;

    @Column(name="pp_principal")
    private boolean principal;

    public PessoaPet() {
    }

    public PessoaPet(Pessoa pessoa, Pet pet, boolean principal) {
        this.pessoa = pessoa;
        this.pet = pet;
        this.principal = principal;
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

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    @Override
    public String toString() {
        return "PessoaPet{" +
                "pessoaId=" + (pessoa != null ? pessoa.getId() : null) +
                ", petId=" + (pet != null ? pet.getId() : null) +
                ", principal=" + principal +
                '}';
    }

}
