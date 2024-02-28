package com.example.travsky.dto;

import com.example.travsky.modelo.Servicio;
import com.example.travsky.views.ServiceView;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;

/**
 * @author Matias
 */
public class ServiciosDelPaquete {
    
    private String nombre;
    private List<Servicio> servicios;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }
    
}