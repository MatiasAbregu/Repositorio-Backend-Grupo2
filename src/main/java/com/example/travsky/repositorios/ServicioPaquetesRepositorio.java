package com.example.travsky.repositorios;

import com.example.travsky.modelo.ServiciosPaquetes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Matias
 */
@Repository
public interface ServicioPaquetesRepositorio extends JpaRepository<ServiciosPaquetes, Integer>{
    
}
