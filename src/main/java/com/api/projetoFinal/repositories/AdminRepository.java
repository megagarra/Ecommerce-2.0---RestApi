package com.api.projetoFinal.repositories;

import com.api.projetoFinal.domain.Admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
