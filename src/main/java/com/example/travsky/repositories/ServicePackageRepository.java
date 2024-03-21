package com.example.travsky.repositories;

import com.example.travsky.models.ServicePackage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la gestión de servicios asociados a un paquete en la base de datos.
 * Proporciona métodos para realizar operaciones CRUD y buscar los servicios por el nombre de paquete.
 */
@Repository
public interface ServicePackageRepository extends JpaRepository<ServicePackage, Integer>{
    
    /**
     * Busca los servicios por el nombre de paquete.
     * @param packageName Nombre del paquete a buscar.
     * @return Una lista de paquetes-servicios que coinciden con el nombre especificado.
     */
    List<ServicePackage> findByPackageName(com.example.travsky.models.Package packageName);
}
