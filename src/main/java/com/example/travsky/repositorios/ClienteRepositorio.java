package com.example.travsky.repositorios;

import com.example.travsky.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Matias
 */
@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer>{
    
}
