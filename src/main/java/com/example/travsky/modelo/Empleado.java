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
    private int codigo;

    @OneToOne
    @JoinColumn(name = "dni")
    private Persona persona;

    @Column(name = "e_cel")
    private String cel;

    @Column(name = "e_email")
    private String email;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "sueldo")
    private int sueldo;

    @OneToMany(mappedBy = "empleado")
    private List<Ventas> listadoVentas;

    public Empleado() {
    }

    public Empleado(int codigo, String cel, String email, String cargo, int sueldo) {
        this.codigo = codigo;
        this.cel = cel;
        this.email = email;
        this.cargo = cargo;
        this.sueldo = sueldo;
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