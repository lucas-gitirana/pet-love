package com.pet_love.demo.repository;

import com.pet_love.demo.model.PessoaPet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaPetRepository extends JpaRepository<PessoaPet, Long> {
    Optional<PessoaPet> findByPetIdAndPrincipalTrue(Long petId);
    List<PessoaPet> findByPessoaId(Long id);
}
