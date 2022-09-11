package com.fin.banco.backend.controller;

import com.fin.banco.backend.model.Movimiento;
import com.fin.banco.backend.response.MovimientoResponse;
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
    public ResponseEntity<MovimientoResponse> listar(){
        ResponseEntity<MovimientoResponse> response = movimientoService.listar();
        return response;
    }

    @PostMapping("/movimientos")
    public ResponseEntity<MovimientoResponse> crear(@RequestBody Movimiento movimiento){
        ResponseEntity<MovimientoResponse> response = movimientoService.crear(movimiento);
        return response;
    }

    @PutMapping("/movimientos/{id}")
    public ResponseEntity<MovimientoResponse> editar(@RequestBody Movimiento movimiento, @PathVariable Long id){
        ResponseEntity<MovimientoResponse> response = movimientoService.editar(movimiento,id);
        return response;
    }

    @DeleteMapping("/movimientos/{id}")
    public ResponseEntity<MovimientoResponse> eliminar ( @PathVariable Long id){
        ResponseEntity<MovimientoResponse> response = movimientoService.eliminar(id);
        return response;
    }
}
