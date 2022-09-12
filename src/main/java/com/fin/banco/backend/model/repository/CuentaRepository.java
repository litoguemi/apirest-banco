package com.fin.banco.backend.model.repository;

import com.fin.banco.backend.model.CuentaDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<CuentaDTO, Long> {

}
