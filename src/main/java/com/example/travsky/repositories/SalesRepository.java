package com.example.travsky.repositories;

import com.example.travsky.models.Employee;
import com.example.travsky.models.Sales;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la gestión de ventas en la base de datos.
 * Proporciona métodos para realizar operaciones CRUD en la entidad Sales.
 */
public interface SalesRepository extends JpaRepository<Sales, Integer>{
    
    /**
     * Busca todos las ventas realizadas por el empleado recibido por parametros.
     * @param employee Empledo a buscar.
     * @return Un lista que contiene todas las ventas realizadas por el empleado.
     */
    List<Sales> findAllByEmployee(Employee employee);
}
