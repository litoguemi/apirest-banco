package com.fin.banco.backend.service;

import com.fin.banco.backend.model.Cliente;
import org.springframework.http.ResponseEntity;

public interface ClienteService {

    public ResponseEntity listar();
    public ResponseEntity crear(Cliente cliente);

    public ResponseEntity editar(Cliente cliente, Long id);

    public ResponseEntity eliminar(Long id);
}
