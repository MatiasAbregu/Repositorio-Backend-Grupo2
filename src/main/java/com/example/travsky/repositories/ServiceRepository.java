package com.example.travsky.repositories;

import com.example.travsky.models.Service;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Matias
 */
@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer>{
    
    List<Service> findByType(String type);
}