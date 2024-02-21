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
@Table(name = "empleados")
public class Empleado {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "e_codigo")
    private int e_codigo;

    @OneToOne
    @JoinColumn(name = "e_dni")
    private Persona persona;

    @Column(name = "e_cel")
    private String e_cel;

    @Column(name = "e_email")
    private String e_email;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "sueldo")
    private int sueldo;

    @OneToMany(mappedBy = "empleado")
    private List<Ventas> listadoVentas;

    public Empleado() {
    }

    public Empleado(int e_codigo, Persona persona, String e_cel, String e_email, String cargo, int sueldo) {
        this.e_codigo = e_codigo;
        this.persona = persona;
        this.e_cel = e_cel;
        this.e_email = e_email;
        this.cargo = cargo;
        this.sueldo = sueldo;
    }

    public int getE_codigo() {
        return e_codigo;
    }

    public void setE_codigo(int e_codigo) {
        this.e_codigo = e_codigo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getE_cel() {
        return e_cel;
    }

    public void setE_cel(String e_cel) {
        this.e_cel = e_cel;
    }

    public String getE_email() {
        return e_email;
    }

    public void setE_email(String e_email) {
        this.e_email = e_email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public List<Ventas> getListadoVentas() {
        return listadoVentas;
    }

    public void setListadoVentas(List<Ventas> listadoVentas) {
        this.listadoVentas = listadoVentas;
    }
    
}