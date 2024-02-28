package com.example.travsky.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private int p_codigo;
    
    @Column(name = "p_nombre")
    private String p_nombre;

    @OneToMany(mappedBy = "paquete")
    @JsonIgnore
    private List<ServiciosPaquetes> servPaq;
    
    public Paquete() {
    }

    public Paquete(int p_codigo, String p_nombre) {
        this.p_codigo = p_codigo;
        this.p_nombre = p_nombre;
    }

    public int getP_codigo() {
        return p_codigo;
    }

    public void setP_codigo(int p_codigo) {
        this.p_codigo = p_codigo;
    }

    public String getP_nombre() {
        return p_nombre;
    }

    public void setP_nombre(String p_nombre) {
        this.p_nombre = p_nombre;
    }

    public List<ServiciosPaquetes> getServPaq() {
        return servPaq;
    }

    public void setServPaq(List<ServiciosPaquetes> servPaq) {
        this.servPaq = servPaq;
    }

}