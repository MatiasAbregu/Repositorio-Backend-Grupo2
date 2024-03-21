package com.example.travsky.repositories;

import com.example.travsky.models.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la gestión de paquetes en la base de datos.
 * Proporciona métodos para realizar operaciones CRUD en la entidad Package.
 */
@Repository
public interface PackageRepository extends JpaRepository<Package, Integer>{
    
}
