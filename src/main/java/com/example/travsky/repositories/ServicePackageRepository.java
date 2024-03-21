package com.example.travsky.repositories;

import com.example.travsky.models.ServicePackage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Matias
 */
@Repository
public interface ServicePackageRepository extends JpaRepository<ServicePackage, Integer>{
    
    List<ServicePackage> findByPackageName(com.example.travsky.models.Package packageName);
}
