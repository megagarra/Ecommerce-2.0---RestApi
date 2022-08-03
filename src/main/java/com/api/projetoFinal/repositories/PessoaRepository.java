package com.api.projetoFinal.repositories;

import com.api.projetoFinal.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

        Optional<Pessoa> findByEmail(String email);

}


