package com.example.travsky.dto;

import com.example.travsky.modelo.Empleado;
import com.example.travsky.modelo.Persona;

/**
 * @author Matias
 */
public class EmpleadoPersonaRequest {
    
    private Empleado empleado;
    private Persona persona;

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}
