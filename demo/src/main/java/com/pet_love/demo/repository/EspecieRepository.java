package com.pet_love.demo.repository;

import com.pet_love.demo.model.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, Long>  {
}
