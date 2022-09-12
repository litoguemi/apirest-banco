package com.fin.banco.backend.controller;

import com.fin.banco.backend.response.Cliente;
import com.fin.banco.backend.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @GetMapping("/clientes")
    public ResponseEntity<Cliente> listar(){
        ResponseEntity<Cliente> response = clienteService.listar();
        return response;
    }
    @PostMapping("/clientes")
    public ResponseEntity<Cliente> crear(@RequestBody Cliente cliente){
        ResponseEntity<Cliente> response = clienteService.crear(cliente);
        return response;
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> editar(@RequestBody Cliente cliente, @PathVariable Long id){
        ResponseEntity<Cliente> response = clienteService.editar(cliente,id);
        return response;
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Cliente> eliminar(@PathVariable Long id){
        ResponseEntity<Cliente> response = clienteService.eliminar(id);
        return response;
    }

}
