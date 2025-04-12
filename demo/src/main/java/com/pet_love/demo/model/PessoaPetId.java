package com.pet_love.demo.model;

import java.util.Objects;

public class PessoaPetId {

    private Long pessoa;
    private Long pet;

    public PessoaPetId() {}

    public PessoaPetId(Long pessoa, Long pet) {
        this.pessoa = pessoa;
        this.pet = pet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PessoaPetId)) return false;
        PessoaPetId that = (PessoaPetId) o;
        return Objects.equals(pessoa, that.pessoa) &&
                Objects.equals(pet, that.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pessoa, pet);
    }

}
