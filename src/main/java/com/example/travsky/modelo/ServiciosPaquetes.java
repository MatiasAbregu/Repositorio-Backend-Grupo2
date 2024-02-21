package com.example.travsky.modelo;

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
    @JoinColumn(name = "codigoPaq")
    private Paquete paquetePaq;
    
    @ManyToOne
    @JoinColumn(name = "codigoServ")
    private Servicio servicioServ;

    public ServiciosPaquetes() {
    }

    public int getCodigoSP() {
        return codigoSP;
    }

    public void setCodigoSP(int codigoSP) {
        this.codigoSP = codigoSP;
    }

    public Paquete getPaquetePaq() {
        return paquetePaq;
    }

    public void setPaquetePaq(Paquete paquetePaq) {
        this.paquetePaq = paquetePaq;
    }

    public Servicio getServicioServ() {
        return servicioServ;
    }

    public void setServicioServ(Servicio servicioServ) {
        this.servicioServ = servicioServ;
    }
 
}