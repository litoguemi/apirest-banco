package com.fin.banco.backend.controller;

import com.fin.banco.backend.model.Cuenta;
import com.fin.banco.backend.response.CuentaResponse;
import com.fin.banco.backend.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class CuentaController {

    @Autowired
    CuentaService cuentaService;

    @GetMapping("/cuentas")
    public ResponseEntity<CuentaResponse> listar(){
        ResponseEntity<CuentaResponse> response = cuentaService.listar();
        return response;
    }

    @PostMapping("/cuentas")
    public ResponseEntity<CuentaResponse> crear(@RequestBody Cuenta cuenta){
        ResponseEntity<CuentaResponse> response = cuentaService.crear(cuenta);
        return response;
    }

    @PutMapping("/cuentas/{id}")
    public ResponseEntity<CuentaResponse> editar(@RequestBody Cuenta cuenta, @PathVariable Long id){
        ResponseEntity<CuentaResponse> response = cuentaService.editar(cuenta,id);
        return response;
    }

    @DeleteMapping("/cuentas/{id}")
    public ResponseEntity<CuentaResponse> eliminar ( @PathVariable Long id){
        ResponseEntity<CuentaResponse> response = cuentaService.eliminar(id);
        return response;
    }
}
