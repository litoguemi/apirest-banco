package com.fin.banco.backend.service;

import com.fin.banco.backend.response.Cuenta;
import org.springframework.http.ResponseEntity;

public interface CuentaService {

    ResponseEntity listar();

    ResponseEntity crear(Cuenta cuenta);

    ResponseEntity editar(Cuenta cuenta, Long id);

    ResponseEntity eliminar(Long id);

}
