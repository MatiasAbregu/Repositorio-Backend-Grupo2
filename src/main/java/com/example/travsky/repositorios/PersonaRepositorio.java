package com.example.travsky.repositorios;

import com.example.travsky.modelo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Matias
 */

@Repository
public interface PersonaRepositorio extends JpaRepository<Persona, Integer>{
    
}
