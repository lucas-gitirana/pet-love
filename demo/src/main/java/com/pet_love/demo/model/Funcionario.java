package com.pet_love.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Funcionario extends Pessoa{

    @Column(name="fun_crmv")
    private String crmv;

    @Column(name="fun_funcao")
    private String funcao;

    public Funcionario() {
        super();
    }

    public Funcionario(Long id, String nome, String cpf, String cidade, String telefone, String email, List<PessoaPet> pets, String crmv, String funcao) {
        super(id, nome, cpf, cidade, telefone, email, pets);
        this.crmv = crmv;
        this.funcao = funcao;
    }

    public String getCrmv() {
        return crmv;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "crmv='" + crmv + '\'' +
                ", funcao='" + funcao + '\'' +
                '}';
    }
}
