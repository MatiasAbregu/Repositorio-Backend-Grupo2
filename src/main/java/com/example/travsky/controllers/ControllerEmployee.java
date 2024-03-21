package com.example.travsky.controllers;

import com.example.travsky.models.Employee;
import com.example.travsky.models.Token;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.travsky.services.EmployeeService;
import com.example.travsky.services.TokenService;
import com.example.travsky.views.EmployeeView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatusCode;

/**
 * @author Matias
 */
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ControllerEmployee {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/auth/get-employees")
    @JsonView(EmployeeView.SimpleEmployee.class)
    private ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
    
    @GetMapping("/auth/employees")
    private ResponseEntity<List<Employee>> getAllEmployeesWithAllInfo(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/auth/employees/{id}")
    private ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PostMapping("/auth/employees")
    private ResponseEntity<?> createEmployee(@RequestBody Employee request) {
        try {
            return ResponseEntity.ok(tokenService.registerEmployee(request, 0));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(e.getMessage());
        }
    }

    @PostMapping("/auth/employees/{dni}")
    private Token createEmployeeAndUpdateUser(@PathVariable int dni, @RequestBody Employee request) {
        return tokenService.registerEmployee(request, dni);
    }

    @PutMapping("/auth/employees/{id}")
    private ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee request) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, request));
    }

    @DeleteMapping("/auth/employees/{id}")
    private ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable int id, @RequestParam int operation) {
        try {
            return ResponseEntity.ok(employeeService.deleteEmployee(id, operation));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }
    }
}
