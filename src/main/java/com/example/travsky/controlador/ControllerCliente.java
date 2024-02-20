package com.example.travsky.controlador;

import com.example.travsky.modelo.Cliente;
import com.example.travsky.modelo.ClienteRepositorio;
import com.example.travsky.modelo.PersonaRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Matias
 */

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ControllerCliente {
    
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    
    @GetMapping("/clientes")
    private List<Cliente> getAllClientes(){
        return clienteRepositorio.findAll();
    }
}