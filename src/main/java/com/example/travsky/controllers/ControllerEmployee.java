package com.example.travsky.controllers;

import com.example.travsky.dto.EmployeePersonRequest;
import com.example.travsky.models.Employee;
import com.example.travsky.models.Person;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.travsky.repositories.EmployeeRepository;
import com.example.travsky.repositories.PersonRepository;

/**
 * @author Matias
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ControllerEmployee {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/empleados")
    private List<Employee> getAllEmployees() {
        return employeeRepository.findAll();     
    }

    @GetMapping("/empleados/{id}")
    private ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        Employee e = employeeRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(e);
    }

    @PostMapping("/empleados-persona")
    private Employee createEmployeeAndPerson(@RequestBody EmployeePersonRequest request) {
        Person p = request.getPerson();
        personRepository.save(p);
        
        Employee e = request.getEmployee();
        e.setPerson(p);
        return employeeRepository.save(e);
    }

    @PostMapping("/empleados")
    private Employee createEmployee(@RequestBody Employee request) {
        return employeeRepository.save(request);
    }

    @PutMapping("/empleados-persona/{id}")
    private ResponseEntity<Employee> updateEmployeeAndPerson(@PathVariable int id, @RequestBody EmployeePersonRequest request) {
        Employee e = employeeRepository.findById(id).orElseThrow();
        e.setJob(request.getEmployee().getJob());
        e.setIncome(request.getEmployee().getIncome());

        Person p = personRepository.findById(request.getPerson().getDni()).orElseThrow();
        p.setFistName(request.getPerson().getFistName());
        p.setLastName(request.getPerson().getLastName());
        p.setBirthdate(request.getPerson().getBirthdate());
        p.setAddress(request.getPerson().getAddress());
        p.setNationality(request.getPerson().getNationality());

        personRepository.save(p);
        e.setPerson(p);
        Employee eA = employeeRepository.save(e);

        return ResponseEntity.ok(eA);
    }

    @PutMapping("/empleados/{id}")
    private ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee request) {
        Employee e = employeeRepository.findById(id).orElseThrow();
        Person p = personRepository.findById(request.getPerson().getDni()).orElseThrow();

        e.setPerson(p);
        Employee eA = employeeRepository.save(e);

        return ResponseEntity.ok(eA);
    }

    @DeleteMapping("/empleados/{id}")
    private ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable int id) {
        Employee e = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(e);

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(respuesta);
    }
}
