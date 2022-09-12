package com.fin.banco.backend.service;

import com.fin.banco.backend.request.ReporteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public interface ReporteService {

    ResponseEntity generaReporteMovimiento(ReporteRequest reporteRequest);

}
