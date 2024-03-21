package com.example.travsky.repositories;

import com.example.travsky.models.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author matia
 */

public interface SalesRepository extends JpaRepository<Sales, Integer>{
    
}
