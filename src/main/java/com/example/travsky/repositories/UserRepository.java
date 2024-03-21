package com.example.travsky.repositories;

import com.example.travsky.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la gestión de usuarios en la base de datos.
 * Proporciona métodos para realizar operaciones CRUD y buscar usuarios por nombre de usuario.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
    /**
     * Busca un usuario por su nombre de usuario.
     * @param username Nombre de usuario a buscar.
     * @return Un Optional que contiene el usuario si se encuentra, o un Optional vacío si no se encuentra.
     */
    Optional<User> findByUsername(String username);
}