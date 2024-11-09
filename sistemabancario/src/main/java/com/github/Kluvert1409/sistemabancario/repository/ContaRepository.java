package com.github.Kluvert1409.sistemabancario.repository;

import com.github.Kluvert1409.sistemabancario.model.Conta;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

}