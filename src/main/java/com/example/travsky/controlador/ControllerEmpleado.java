package com.example.travsky.controlador;

import com.example.travsky.modelo.Empleado;
import com.example.travsky.repositorios.EmpleadoRepositorio;
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
public class ControllerEmpleado {
    
    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;
    
    @GetMapping("/empleados")
    private List<Empleado> getAllEmpleados(){
        return empleadoRepositorio.findAll();
    }
    
    
}