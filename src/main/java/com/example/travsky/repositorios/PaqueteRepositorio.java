package com.example.travsky.repositorios;

import com.example.travsky.modelo.Paquete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Matias
 */

@Repository
public interface PaqueteRepositorio extends JpaRepository<Paquete, Integer>{
    
}
