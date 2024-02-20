package com.example.travsky.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 * @author Matias
 */

@Entity
@Table(name = "paquetes")
public class Paquete {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "p_codigo")
    private int codigo;
    
    @Column(name = "p_nombre")
    private String nombre;

    @OneToMany(mappedBy = "paquete")
    private List<ServiciosPaquetes> servPaq;
    
    public Paquete() {
    }

    public Paquete(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ServiciosPaquetes> getServPaq() {
        return servPaq;
    }

    public void setServPaq(List<ServiciosPaquetes> servPaq) {
        this.servPaq = servPaq;
    }
    
}