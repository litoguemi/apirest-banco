package com.fin.banco.backend.controller;

import com.fin.banco.backend.model.Cliente;
import com.fin.banco.backend.response.ClienteResponse;
import com.fin.banco.backend.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/clientes")
    public ResponseEntity<ClienteResponse> crear(@RequestBody Cliente cliente){
        ResponseEntity<ClienteResponse> response = clienteService.crear(cliente);
        return response;
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<ClienteResponse> editar(@RequestBody Cliente cliente, @PathVariable Long id){
        ResponseEntity<ClienteResponse> response = clienteService.editar(cliente,id);
        return response;
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<ClienteResponse> eliminar(@PathVariable Long id){
        ResponseEntity<ClienteResponse> response = clienteService.eliminar(id);
        return response;
    }

}
