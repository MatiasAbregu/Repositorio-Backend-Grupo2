package com.example.travsky.repositories;

import com.example.travsky.models.Client;
import com.example.travsky.models.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la gestión de clientes en la base de datos.
 * Proporciona métodos para realizar operaciones CRUD en la entidad Client.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{
    
    /**
     * Busca un cliente por la persona asociada.
     * @param person Persona asociada al cliente.
     * @return Un Optional que contiene el cliente si se encuentra, o un Optional vacío si no se encuentra.
     */
    Optional<Client> findByPerson(Person person);
}
