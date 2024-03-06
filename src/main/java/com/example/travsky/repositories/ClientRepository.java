package com.example.travsky.repositories;

import com.example.travsky.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Matias
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{
    
}
