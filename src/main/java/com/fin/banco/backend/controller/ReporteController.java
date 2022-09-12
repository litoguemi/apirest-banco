package com.fin.banco.backend.controller;

import com.fin.banco.backend.request.ReporteRequest;
import com.fin.banco.backend.response.Reporte;
import com.fin.banco.backend.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/v1")
public class ReporteController {

    @Autowired
    ReporteService reporteService;


    @GetMapping(value = "/reportes",params = {"fechaInicio","fechaFin","clienteId"})
    public ResponseEntity<Reporte> generaReporte(@RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin")  String fechaFin, @RequestParam("clienteId") Long clienteId){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaInicioD = LocalDate.parse(fechaInicio, formatter);
        LocalDate fechaFinD = LocalDate.parse(fechaFin, formatter);

        ReporteRequest reporteRequest = new ReporteRequest(fechaInicioD, fechaFinD, clienteId);
        ResponseEntity<Reporte> response = reporteService.generaReporteMovimiento(reporteRequest);
        return response;
    }
}
