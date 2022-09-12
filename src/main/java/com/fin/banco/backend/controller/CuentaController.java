package com.fin.banco.backend.controller;

import com.fin.banco.backend.response.Cuenta;
import com.fin.banco.backend.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class CuentaController {

    @Autowired
    CuentaService cuentaService;

    @GetMapping(value = "/cuentas")
    public ResponseEntity<Cuenta> listar(){
        ResponseEntity<Cuenta> response = cuentaService.listar();
        return response;
    }

    @PostMapping("/cuentas")
    public ResponseEntity<Cuenta> crear(@RequestBody Cuenta cuenta){
        ResponseEntity<Cuenta> response = cuentaService.crear(cuenta);
        return response;
    }

    @PutMapping("/cuentas/{id}")
    public ResponseEntity<Cuenta> editar(@RequestBody Cuenta cuenta, @PathVariable Long id){
        ResponseEntity<Cuenta> response = cuentaService.editar(cuenta,id);
        return response;
    }

    @DeleteMapping("/cuentas/{id}")
    public ResponseEntity<Cuenta> eliminar (@PathVariable Long id){
        ResponseEntity<Cuenta> response = cuentaService.eliminar(id);
        return response;
    }
}
