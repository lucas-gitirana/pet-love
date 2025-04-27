package com.pet_love.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public class PessoaPetDTO {
    private Long pessoa_id;
    private Long pet_id;
    private boolean principal;

    public PessoaPetDTO() {
    }

    public PessoaPetDTO(Long pessoaId, Long petId, boolean principal) {
        this.pessoa_id = pessoaId;
        this.pet_id = petId;
        this.principal = principal;
    }

    public Long getPessoaId() {
        return pessoa_id;
    }

    public void setPessoaId(Long pessoa_id) {
        this.pessoa_id = pessoa_id;
    }

    public Long getPetId() {
        return pet_id;
    }

    public void setPetId(Long pet_id) {
        this.pet_id = pet_id;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    @Override
    public String toString() {
        return "PessoaPetDTO{" +
                "pessoaId=" + pessoa_id +
                ", petId=" + pet_id +
                ", principal=" + principal +
                '}';
    }
}
