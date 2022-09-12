package com.fin.banco.backend.response;

import com.fin.banco.backend.model.ClienteDTO;

public class Cliente extends Persona{

    private static final long serialVersionUID = 1L;

    private Long clienteId;
    private String contrasena;
    private Boolean estado;

    public Cliente() {
        super();
    }

    public Cliente(Long id, String nombre, String genero, Integer edad, String identificacion, String direccion, String telefono, Long clienteId, String contrasena, Boolean estado) {
        super(id, nombre, genero, edad, identificacion, direccion, telefono);
        this.clienteId = clienteId;
        this.contrasena = contrasena;
        this.estado = estado;
    }


    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
