package com.api.projetoFinal.repositories;

import com.api.projetoFinal.domain.Produto;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Optional<Produto> findById(Integer id);
    @Query(value = "select u from Produto u where lower(trim(u.name)) like %?1%")
   List<Produto> buscarPorNome(String name );
    
    @Query(value = "select u from Produto u where lower(trim(u.categoria)) like %?1%")
    List<Produto> buscarPorCategoria(String categoria );

    @Query(value="call SPS_CRIACAO_PRODUTOS_MES(:mes)", nativeQuery = true)
    List<Produto> listarProdMes( Integer mes);
    
    @Query(value="CALL sps_produtos_byWeek(:semana)", nativeQuery = true)
    List<Produto> listarProdSemana(Integer semana);
}