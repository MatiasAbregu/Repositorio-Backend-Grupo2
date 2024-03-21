package com.example.travsky.repositories;

import com.example.travsky.models.Client;
import com.example.travsky.models.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Matias
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{
    Optional<Client> findByPerson(Person person);
}
