package com.example.travsky.controllers;

import com.example.travsky.models.Person;
import com.example.travsky.models.Token;
import com.example.travsky.models.User;
import com.example.travsky.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para la gestión de usuarios en la aplicación.
 */
@RestController
public class ControllerUsers {

    @Autowired
    private UserService userService;

    /**
     * Inicio de sesión de un usuario.
     * @param request La información de inicio de sesión del usuario.
     * @return ResponseEntity con el token de acceso si el inicio de sesión es exitoso,
     * o un ResponseEntity con el código de estado 400 si ocurre un error durante el inicio de sesión.
     */
    @PostMapping("/user/login")
    private ResponseEntity<Token> getUser(@RequestBody User request) {
        try {
            return ResponseEntity.ok(userService.login(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }
    }

    /**
     * Registra un nuevo usuario.
     * @param request La información del nuevo usuario.
     * @return ResponseEntity con la persona creada,
     * o un ResponseEntity con el código de estado 400 si ocurre un error durante el registro.
     */
    @PostMapping("/user")
    private ResponseEntity<Person> createUser(@RequestBody Person request) {
        try {
            return ResponseEntity.ok(userService.register(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }
    }
}
