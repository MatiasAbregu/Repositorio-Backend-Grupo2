package com.example.travsky.controlador;

import com.example.travsky.dto.ClientePersonaRequest;
import com.example.travsky.modelo.Cliente;
import com.example.travsky.modelo.Persona;
import com.example.travsky.repositorios.ClienteRepositorio;
import com.example.travsky.repositorios.PersonaRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Matias
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ControllerCliente {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private PersonaRepositorio personaRepositorio;

    @GetMapping("/clientes")
    private List<Cliente> getAllClients() {
        return clienteRepositorio.findAll();
    }

    @GetMapping("/clientes/{id}")
    private ResponseEntity<Cliente> getClientById(@PathVariable int id) {
        Cliente c = clienteRepositorio.findById(id).orElseThrow();
        return ResponseEntity.ok(c);
    }

    @PostMapping("/clientes-persona")
    private Cliente createClientAndPerson(@RequestBody ClientePersonaRequest request) {
        Cliente c = request.getCliente();  
        personaRepositorio.save(request.getPersona());
        c.setPersona(request.getPersona());
        return clienteRepositorio.save(c);
    }
    
    @PostMapping("/clientes")
    private Cliente createClient(@RequestBody Cliente request){  
        return clienteRepositorio.save(request);
    }

    @PutMapping("/clientes/{id}")
    private ResponseEntity<Cliente> updateClientAndOrPerson(@PathVariable int id ,@RequestBody ClientePersonaRequest request) {
        Cliente c = clienteRepositorio.findById(id).orElseThrow();
        c.setC_cel(request.getCliente().getC_cel());
        c.setC_email(request.getCliente().getC_email());
        
        Persona p = c.getPersona();
        p.setDni(request.getPersona().getDni());
        p.setNombre(request.getPersona().getNombre());
        p.setApellido(request.getPersona().getApellido());
        p.setFecha_nac(request.getPersona().getFecha_nac());
        p.setDireccion(request.getPersona().getDireccion());
        p.setNacionalidad(request.getPersona().getNacionalidad());
        
        personaRepositorio.save(p);
        c.setPersona(p);
        Cliente cA = clienteRepositorio.save(c);
        
        return ResponseEntity.ok(cA);
    }

    @DeleteMapping("/clientes/{id}")
    private String hol() {
        return "";
    }

}
