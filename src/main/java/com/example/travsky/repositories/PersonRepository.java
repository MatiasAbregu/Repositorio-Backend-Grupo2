package com.example.travsky.repositories;

import com.example.travsky.models.Person;
import com.example.travsky.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Matias
 */

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{
    Optional<Person> findByUser(User user); 
}
