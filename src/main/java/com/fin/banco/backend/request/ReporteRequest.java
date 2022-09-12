package com.fin.banco.backend.request;

import java.io.Serializable;
import java.time.LocalDate;

public class ReporteRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private Long clienteId;

    private ReporteRequest() {
    }

    public ReporteRequest(LocalDate fechaInicial, LocalDate fechaFinal, Long clienteId) {
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.clienteId = clienteId;
    }

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
}
