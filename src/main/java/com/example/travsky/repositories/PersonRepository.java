package com.example.travsky.repositories;

import com.example.travsky.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Matias
 */

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{
    
}
