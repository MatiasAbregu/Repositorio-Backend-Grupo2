package com.example.travsky.controllers;

import com.example.travsky.models.*;
import com.example.travsky.services.ClientService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para la gestión de clientes en la aplicación.
 */
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ControllerClient {

    @Autowired
    private ClientService clientService;
    
    /**
     * Obtiene todos los clientes.
     * Solo rol admin y employee pueden acceder a ella.
     * @return ResponseEntity con la lista de clientes.
     */
    @GetMapping("/auth/clients")
    private ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    /**
     * Obtiene un cliente por su ID.
     * Solo rol admin y employee pueden acceder a ella.
     * @param id ID del cliente a obtener.
     * @return ResponseEntity con el cliente.
     */
    @GetMapping("/auth/clients/{id}")
    private ResponseEntity<Client> getClientById(@PathVariable int id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }
    
    /**
     * Crea una venta a partir de un cliente si existe, sino se crea.
     * Cualquier rol puede acceder a ella.
     * @param request La información de la venta.
     * @return ResponseEntity con la venta creada.
     */
    @PostMapping("/clients/sale")
    private ResponseEntity<Sales> createSaleFromClient(@RequestBody Sales request){
        return ResponseEntity.ok(clientService.createSale(request));
    }
    
}