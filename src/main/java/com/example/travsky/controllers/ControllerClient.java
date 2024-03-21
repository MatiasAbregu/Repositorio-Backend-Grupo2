package com.example.travsky.controllers;

import com.example.travsky.models.*;
import com.example.travsky.services.ClientService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Matias
 */
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ControllerClient {

    @Autowired
    private ClientService clientService;
    
    @GetMapping("/auth/clients")
    private ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/auth/clients/{id}")
    private ResponseEntity<Client> getClientById(@PathVariable int id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }
    
    @PostMapping("/clients/sale")
    private ResponseEntity<Sales> createSaleFromClient(@RequestBody Sales request){
        return ResponseEntity.ok(clientService.createSale(request));
    }
    
}