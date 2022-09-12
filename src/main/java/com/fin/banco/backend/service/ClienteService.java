package com.fin.banco.backend.service;

import com.fin.banco.backend.response.Cliente;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClienteService {

    ResponseEntity listar();
    ResponseEntity crear(Cliente cliente);

    ResponseEntity editar(Cliente cliente, Long id);

    ResponseEntity eliminar(Long id);
}
