package com.example.travsky.controlador;

import com.example.travsky.dto.EmpleadoPersonaRequest;
import com.example.travsky.modelo.Empleado;
import com.example.travsky.modelo.Persona;
import com.example.travsky.repositorios.EmpleadoRepositorio;
import com.example.travsky.repositorios.PersonaRepositorio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class ControllerEmpleado {

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    @Autowired
    private PersonaRepositorio personaRepositorio;

    @GetMapping("/empleados")
    private List<Empleado> getAllEmployees() {
        return empleadoRepositorio.findAll();
    }

    @GetMapping("/empleados/{id}")
    private ResponseEntity<Empleado> getEmployeeById(@PathVariable int id) {
        Empleado e = empleadoRepositorio.findById(id).orElseThrow();
        return ResponseEntity.ok(e);
    }

    @PostMapping("/empleados-persona")
    private Empleado createEmployeeAndPerson(@RequestBody EmpleadoPersonaRequest request) {
        Persona p = request.getPersona();
        personaRepositorio.save(p);
        
        Empleado e = request.getEmpleado();
        e.setPersona(p);
        return empleadoRepositorio.save(e);
    }

    @PostMapping("/empleados")
    private Empleado createEmployee(@RequestBody Empleado request) {
        return empleadoRepositorio.save(request);
    }

    @PutMapping("/empleados-persona/{id}")
    private ResponseEntity<Empleado> updateEmployeeAndPerson(@PathVariable int id, @RequestBody EmpleadoPersonaRequest request) {
        Empleado e = empleadoRepositorio.findById(id).orElseThrow();
        e.setE_cel(request.getEmpleado().getE_cel());
        e.setE_email(request.getEmpleado().getE_email());
        e.setCargo(request.getEmpleado().getCargo());
        e.setSueldo(request.getEmpleado().getSueldo());

        Persona p = personaRepositorio.findById(request.getPersona().getDni()).orElseThrow();
        p.setNombre(request.getPersona().getNombre());
        p.setApellido(request.getPersona().getApellido());
        p.setFecha_nac(request.getPersona().getFecha_nac());
        p.setDireccion(request.getPersona().getDireccion());
        p.setNacionalidad(request.getPersona().getNacionalidad());

        personaRepositorio.save(p);
        e.setPersona(p);
        Empleado eA = empleadoRepositorio.save(e);

        return ResponseEntity.ok(eA);
    }

    @PutMapping("/empleados/{id}")
    private ResponseEntity<Empleado> updateEmployee(@PathVariable int id, @RequestBody Empleado request) {
        Empleado e = empleadoRepositorio.findById(id).orElseThrow();
        Persona p = personaRepositorio.findById(request.getPersona().getDni()).orElseThrow();

        e.setPersona(p);
        Empleado eA = empleadoRepositorio.save(e);

        return ResponseEntity.ok(eA);
    }

    @DeleteMapping("/empleados/{id}")
    private ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable int id) {
        Empleado e = empleadoRepositorio.findById(id).orElseThrow();
        empleadoRepositorio.delete(e);

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(respuesta);
    }
}
