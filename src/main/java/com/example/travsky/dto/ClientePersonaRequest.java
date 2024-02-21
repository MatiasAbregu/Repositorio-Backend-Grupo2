package com.example.travsky.dto;

import com.example.travsky.modelo.Cliente;
import com.example.travsky.modelo.Persona;

/**
 * @author Matias
 */
public class ClientePersonaRequest {
 
    private Cliente cliente;
    private Persona persona;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
   
}