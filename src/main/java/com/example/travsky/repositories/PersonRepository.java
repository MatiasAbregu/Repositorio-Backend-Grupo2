package com.example.travsky.repositories;

import com.example.travsky.models.Person;
import com.example.travsky.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la gestión de personas en la base de datos.
 * Proporciona métodos para realizar operaciones CRUD en la entidad Person.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{
    
    /**
     * Busca una persona por su usuario asociado.
     * @param user Usuario asociado a la persona.
     * @return Un Optional que contiene la persona si se encuentra, o un Optional vacío si no se encuentra.
     */
    Optional<Person> findByUser(User user); 
}
