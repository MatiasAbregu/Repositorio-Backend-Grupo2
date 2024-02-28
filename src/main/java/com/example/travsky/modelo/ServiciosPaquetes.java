package com.example.travsky.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author Matias
 */

@Entity
@Table(name = "serviciosxpaquetes")
public class ServiciosPaquetes {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codigoSP")
    private int codigoSP;
    
    @ManyToOne
    @JoinColumn(name = "codigo_paq")
    @JsonIgnore
    private Paquete paquete;
    
    @ManyToOne
    @JoinColumn(name = "codigo_serv")
    @JsonIgnore
    private Servicio servicio;

    public ServiciosPaquetes() {
    }

    public int getCodigoSP() {
        return codigoSP;
    }

    public void setCodigoSP(int codigoSP) {
        this.codigoSP = codigoSP;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
 
}