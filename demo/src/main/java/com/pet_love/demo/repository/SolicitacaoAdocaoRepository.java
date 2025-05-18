package com.pet_love.demo.repository;

import com.pet_love.demo.model.SolicitacaoAdocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitacaoAdocaoRepository extends JpaRepository<SolicitacaoAdocao, Long> {


}
