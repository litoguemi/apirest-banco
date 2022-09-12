package com.fin.banco.backend.controller;

import com.fin.banco.backend.response.Movimiento;
import com.fin.banco.backend.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class MovimientoController {

    @Autowired
    MovimientoService movimientoService;


    @GetMapping("/movimientos")
    public ResponseEntity<Movimiento> listar(){
        ResponseEntity<Movimiento> response = movimientoService.listar();
        return response;
    }

    @PostMapping("/movimientos")
    public ResponseEntity<Movimiento> crear(@RequestBody Movimiento movimiento){
        ResponseEntity<Movimiento> response = movimientoService.crear(movimiento);
        return response;
    }

    @PutMapping("/movimientos/{id}")
    public ResponseEntity<Movimiento> editar(@RequestBody Movimiento movimiento, @PathVariable Long id){
        ResponseEntity<Movimiento> response = movimientoService.editar(movimiento,id);
        return response;
    }

    @DeleteMapping("/movimientos/{id}")
    public ResponseEntity<Movimiento> eliminar (@PathVariable Long id){
        ResponseEntity<Movimiento> response = movimientoService.eliminar(id);
        return response;
    }
}
