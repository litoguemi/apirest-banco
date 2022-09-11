package com.fin.banco.backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Cliente")
@PrimaryKeyJoinColumn(referencedColumnName ="persona_id")
public class Cliente extends Persona{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long id;
    private String contrasena;
    private Boolean estado;

    public Cliente() {
    }

    public Cliente(Long id, String contrasena, Boolean estado) {
        this.id = id;
        this.contrasena = contrasena;
        this.estado = estado;
    }



    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
