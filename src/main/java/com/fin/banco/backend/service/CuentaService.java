package com.fin.banco.backend.service;

import com.fin.banco.backend.model.Cuenta;
import org.springframework.http.ResponseEntity;

public interface CuentaService {

    public ResponseEntity listar();

    public ResponseEntity crear(Cuenta cuenta);

    public ResponseEntity editar(Cuenta cuenta, Long id);

    public ResponseEntity eliminar(Long id);

}
