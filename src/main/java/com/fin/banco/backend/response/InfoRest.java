package com.fin.banco.backend.response;

public class InfoRest {

    private int codigo;
    private String mensaje;
    private String tipo;

    public InfoRest(int codigo, String mensaje, String tipo) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
