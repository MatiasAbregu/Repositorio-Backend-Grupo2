package com.example.travsky.controllers;

import com.example.travsky.models.Person;
import com.example.travsky.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author Matias
 */

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ControllerUsers {
    
     
    @PostMapping("/user/login")
    private ResponseEntity<String> getUser(@RequestBody User request){
        return ResponseEntity.ok("Hola");
    }
    
    @PostMapping("/user")
    private ResponseEntity<String> createUser(@RequestBody Person request) {
       return ResponseEntity.ok("Hola");
    }
}
