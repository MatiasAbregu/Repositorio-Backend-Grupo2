package com.example.travsky.repositories;

import com.example.travsky.models.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la gestión de ventas en la base de datos.
 * Proporciona métodos para realizar operaciones CRUD en la entidad Sales.
 */
public interface SalesRepository extends JpaRepository<Sales, Integer>{
    
}
