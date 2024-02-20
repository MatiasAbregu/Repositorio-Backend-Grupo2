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
    private int codigo;
    
    @OneToOne
    @JoinColumn(name = "c_dni")
    private Persona persona;
    
    @Column(name = "c_cel")
    private String cel;
    
    @Column(name = "c_email")
    private String email;

    @OneToMany(mappedBy = "cliente")
    private List<Ventas> listadoVentas;
    
    public Cliente() {
    }
    
    public Cliente(int codigo, String cel, String email) {
        this.codigo = codigo;
        this.cel = cel;
        this.email = email;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Ventas> getVentas() {
        return listadoVentas;
    }

    public void setVentas(List<Ventas> listadoVentas) {
        this.listadoVentas = listadoVentas;
    }
  
}