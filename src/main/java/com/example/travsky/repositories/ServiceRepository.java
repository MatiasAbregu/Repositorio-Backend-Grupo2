package com.example.travsky.repositories;

import com.example.travsky.models.Service;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la gestión de servicios en la base de datos.
 * Proporciona métodos para realizar operaciones CRUD y buscar servicios por tipo.
 */
@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer>{
    
    /**
     * Busca servicios por su tipo.
     * @param type Tipo de servicio a buscar.
     * @return Una lista de servicios que coinciden con el tipo especificado.
     */
    List<Service> findByType(String type);
}