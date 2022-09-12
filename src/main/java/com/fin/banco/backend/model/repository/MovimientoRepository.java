package com.fin.banco.backend.model.repository;

import com.fin.banco.backend.model.MovimientoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepository extends JpaRepository<MovimientoDTO, Long> {

}
