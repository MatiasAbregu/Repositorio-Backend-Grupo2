package com.example.travsky.modelo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Matias
 */
@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer>{
    
}
