package com.github.Kluvert1409.sistemabancario.repository;

import com.github.Kluvert1409.sistemabancario.pojos.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

}