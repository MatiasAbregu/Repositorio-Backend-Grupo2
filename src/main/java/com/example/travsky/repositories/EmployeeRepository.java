package com.example.travsky.repositories;

import com.example.travsky.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Matias
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    
}
