package com.example.travsky.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    private int codigo;
    
    @Column(name = "fecha")
    private Date fecha;
    
    @Column(name = "medio_pago")
    private String medioPag;
    
    @ManyToOne
    @JoinColumn(name = "c_codigo")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "s_codigo")
    private Servicio servicio;
    
    @ManyToOne
    @JoinColumn(name = "p_codigo")
    private Paquete paquete;
    
    @ManyToOne
    @JoinColumn(name = "e_codigo")
    private Empleado empleado;
    
}
