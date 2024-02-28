package com.example.travsky.repositorios;

import com.example.travsky.modelo.Servicio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Matias
 */
@Repository
public interface ServicioRepositorio extends JpaRepository<Servicio, Integer>{
    
    List<Servicio> findByTipo(String tipo);
}