package com.example.travsky.repositorios;

import com.example.travsky.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Matias
 */
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Integer>{
    
}
