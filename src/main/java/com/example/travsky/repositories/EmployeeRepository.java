package com.example.travsky.repositories;

import com.example.travsky.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la gestión de empleados en la base de datos.
 * Proporciona métodos para realizar operaciones CRUD en la entidad Employee.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    
}
