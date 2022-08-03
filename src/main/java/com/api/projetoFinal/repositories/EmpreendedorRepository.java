package com.api.projetoFinal.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.projetoFinal.domain.Empreendedor;

public interface EmpreendedorRepository extends JpaRepository<Empreendedor, Integer> {
	
	Optional<Empreendedor> findByCnpj(String cnpj);
    Optional<Empreendedor> findByEmail(String cnpj);
    Optional<Empreendedor> findById(Integer id);

    @Query(value = "select e.id from Empreendedor e where e.email = ?1")
    Integer findIdByEmail(String email);

    @Query(value = "CALL sps_empreendedores_byMonth(:mes)", nativeQuery = true)
    List<Empreendedor> empreendedorPorMes(Integer mes);
}
