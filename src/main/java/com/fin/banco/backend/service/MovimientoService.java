package com.fin.banco.backend.service;

import com.fin.banco.backend.model.Movimiento;
import org.springframework.http.ResponseEntity;

public interface MovimientoService {

    public ResponseEntity listar();

    public ResponseEntity crear(Movimiento movimiento);

    public ResponseEntity editar(Movimiento movimiento, Long id);

    public ResponseEntity eliminar(Long id);
}
