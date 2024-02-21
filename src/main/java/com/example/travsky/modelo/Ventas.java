package com.example.travsky.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

/**
 * @author Matias
 */

@Entity
@Table(name = "ventas")
public class Ventas {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "v_codigo")
    private int v_codigo;
    
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @Column(name = "medio_pago")
    private String medio_pago;
    
    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "servicio")
    private Servicio servicio;
    
    @ManyToOne
    @JoinColumn(name = "paquete")
    private Paquete paquete;
    
    @ManyToOne
    @JoinColumn(name = "empleado")
    private Empleado empleado;

    public Ventas() {
    }

    public Ventas(int v_codigo, Date fecha, String medio_pago) {
        this.v_codigo = v_codigo;
        this.fecha = fecha;
        this.medio_pago = medio_pago;
    }

    public int getV_codigo() {
        return v_codigo;
    }

    public void setV_codigo(int v_codigo) {
        this.v_codigo = v_codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMedio_pago() {
        return medio_pago;
    }

    public void setMedio_pago(String medio_pago) {
        this.medio_pago = medio_pago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
 
}