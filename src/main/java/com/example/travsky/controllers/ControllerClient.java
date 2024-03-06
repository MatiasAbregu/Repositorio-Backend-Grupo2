package com.example.travsky.controllers;

import com.example.travsky.dto.ClientPersonRequest;
import com.example.travsky.models.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.travsky.repositories.*;

/**
 * @author Matias
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ControllerClient {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/clientes")
    private List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/clientes/{id}")
    private ResponseEntity<Client> getClientById(@PathVariable int id) {
        Client c = clientRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(c);
    }

    @PostMapping("/clientes-persona")
    private Client createClientAndPerson(@RequestBody ClientPersonRequest request) {
        Client c = request.getClient();  
        personRepository.save(request.getPerson());
        c.setPerson(request.getPerson());
        return clientRepository.save(c);
    }
    
    @PostMapping("/clientes")
    private Client createClient(@RequestBody Client request){  
        return clientRepository.save(request);
    }

    @PutMapping("/clientes-persona/{id}")
    private ResponseEntity<Client> updateClientAndPerson(@PathVariable int id ,@RequestBody ClientPersonRequest request) {
        
        Client c = clientRepository.findById(id).orElseThrow();


        Person p = personRepository.findById(request.getPerson().getDni()).orElseThrow();
        p.setFistName(request.getPerson().getFistName());
        p.setLastName(request.getPerson().getLastName());
        p.setBirthdate(request.getPerson().getBirthdate());
        p.setAddress(request.getPerson().getAddress());
        p.setNationality(request.getPerson().getNationality());
        
        personRepository.save(p);
        c.setPerson(p);
        
        Client cA = clientRepository.save(c);
        
        return ResponseEntity.ok(cA);
    }
    
    @PutMapping("/clientes/{id}")
    private ResponseEntity<Client> updateClient(@PathVariable int id, @RequestBody Client request){
       Client c = clientRepository.findById(id).orElseThrow();
       Person p = personRepository.findById(request.getPerson().getDni()).orElseThrow();
       
       c.setPerson(p);
       Client cA = clientRepository.save(c);
       
       return ResponseEntity.ok(cA);
    }

    @DeleteMapping("/clientes/{id}")
    private ResponseEntity<Map<String, Boolean>> deleteClient (@PathVariable int id) {
        Client c = clientRepository.findById(id).orElseThrow();
        clientRepository.delete(c);
        
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("deleted", Boolean.TRUE);
        
        return ResponseEntity.ok(respuesta);
    }

}
