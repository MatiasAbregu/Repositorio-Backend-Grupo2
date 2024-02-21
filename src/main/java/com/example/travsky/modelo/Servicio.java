package com.example.travsky.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @author Matias
 */

@Entity
@Table(name = "servicios")
public class Servicio {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "s_codigo")
    private int s_codigo;
    
    @Column(name = "s_nombre")
    private String s_nombre;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "destino")
    private String destino;
    
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @Column(name = "costo")
    private float costo;

    @OneToMany(mappedBy = "servicioServ")
    private List<ServiciosPaquetes> servPaq;
    
    public Servicio() {
    }

    public Servicio(int s_codigo, String s_nombre, String descripcion, String destino, Date fecha, float costo) {
        this.s_codigo = s_codigo;
        this.s_nombre = s_nombre;
        this.descripcion = descripcion;
        this.destino = destino;
        this.fecha = fecha;
        this.costo = costo;
    }

    public int getS_codigo() {
        return s_codigo;
    }

    public void setS_codigo(int s_codigo) {
        this.s_codigo = s_codigo;
    }

    public String getS_nombre() {
        return s_nombre;
    }

    public void setS_nombre(String s_nombre) {
        this.s_nombre = s_nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public List<ServiciosPaquetes> getServPaq() {
        return servPaq;
    }

    public void setServPaq(List<ServiciosPaquetes> servPaq) {
        this.servPaq = servPaq;
    }
    
}