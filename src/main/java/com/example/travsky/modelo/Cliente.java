package com.example.travsky.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;

/**
 * @author Matias
 */

@Entity
@Table(name = "clientes")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_codigo")
    private int c_codigo;
    
    @OneToOne
    @JoinColumn(name = "c_dni")
    private Persona persona;
    
    @Column(name = "c_cel")
    private String c_cel;
    
    @Column(name = "c_email")
    private String c_email;

    @OneToMany(mappedBy = "cliente")
    private List<Ventas> listadoVentas;
    
    public Cliente() {
    }

    public Cliente(int c_codigo, Persona persona, String c_cel, String c_email) {
        this.c_codigo = c_codigo;
        this.persona = persona;
        this.c_cel = c_cel;
        this.c_email = c_email;
    }

    public int getC_codigo() {
        return c_codigo;
    }

    public void setC_codigo(int c_codigo) {
        this.c_codigo = c_codigo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getC_cel() {
        return c_cel;
    }

    public void setC_cel(String c_cel) {
        this.c_cel = c_cel;
    }

    public String getC_email() {
        return c_email;
    }

    public void setC_email(String c_email) {
        this.c_email = c_email;
    }

    public List<Ventas> getListadoVentas() {
        return listadoVentas;
    }

    public void setListadoVentas(List<Ventas> listadoVentas) {
        this.listadoVentas = listadoVentas;
    }
  
}