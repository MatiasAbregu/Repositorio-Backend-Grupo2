package com.example.travsky.controllers;

import com.example.travsky.models.Person;
import com.example.travsky.models.Token;
import com.example.travsky.models.User;
import com.example.travsky.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para la gestión de usuarios en la aplicación.
 */
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ControllerUsers {

    @Autowired
    private TokenService tokenService;

    /**
     * Inicio de sesión de un usuario.
     * @param request La información de inicio de sesión del usuario.
     * @return ResponseEntity con el token de acceso si el inicio de sesión es exitoso,
     * o un ResponseEntity con el código de estado 400 si ocurre un error durante el inicio de sesión.
     */
    @PostMapping("/user/login")
    private ResponseEntity<Token> getUser(@RequestBody User request) {
        try {
            return ResponseEntity.ok(tokenService.login(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }
    }

    /**
     * Registra un nuevo usuario.
     * @param request La información del nuevo usuario.
     * @return ResponseEntity con el token de acceso si el registro es exitoso,
     * o un ResponseEntity con el código de estado 400 si ocurre un error durante el registro.
     */
    @PostMapping("/user")
    private ResponseEntity<Token> createUser(@RequestBody Person request) {
        try {
            return ResponseEntity.ok(tokenService.register(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }
    }
}
