package com.fin.banco.backend.response;

import java.util.List;

public class ResponseRest <T>{

    private List<T> datos;
    private List<InfoRest> infoRestList;

    public List<T> getDatos() {
        return datos;
    }

    public void setDatos(List<T> datos) {
        this.datos = datos;
    }

    public List<InfoRest> getInfoRestList() {
        return infoRestList;
    }

    public void setInfoRestList(List<InfoRest> infoRestList) {
        this.infoRestList = infoRestList;
    }
}
