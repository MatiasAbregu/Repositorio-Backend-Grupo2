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
 * @author Matias
 */
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ControllerUsers {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/user/login")
    private ResponseEntity<Token> getUser(@RequestBody User request) {
        try {
            return ResponseEntity.ok(tokenService.login(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }
    }

    @PostMapping("/user")
    private ResponseEntity<Token> createUser(@RequestBody Person request) {
        try {
            return ResponseEntity.ok(tokenService.register(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }
    }
}
