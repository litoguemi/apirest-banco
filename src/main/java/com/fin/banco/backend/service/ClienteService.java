package com.fin.banco.backend.service;

import com.fin.banco.backend.model.Cliente;
import com.fin.banco.backend.response.ClienteResponse;
import org.springframework.http.ResponseEntity;

public interface ClienteService {


    public ResponseEntity crear(Cliente categoria);

    public ResponseEntity editar(Cliente categoria, Long id);

    public ResponseEntity eliminar(Long id);
}
