package com.example.travsky.repositories;

import com.example.travsky.models.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Matias
 */

@Repository
public interface PackageRepository extends JpaRepository<Package, Integer>{
    
}
