package com.fin.banco.backend.service;

import com.fin.banco.backend.response.Movimiento;
import org.springframework.http.ResponseEntity;

public interface MovimientoService {

    ResponseEntity listar();

    ResponseEntity crear(Movimiento movimiento);

    ResponseEntity editar(Movimiento movimiento, Long id);

    ResponseEntity eliminar(Long id);
}
