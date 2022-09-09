package com.fin.banco.backend.model.repository;

import com.fin.banco.backend.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

}
