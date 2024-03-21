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
 * Controlador para la gestión de empleados en la aplicación.
 */
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ControllerEmployee {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TokenService tokenService;

    /**
     * Obtiene todos los empleados con información básica (Ver más en Views).
     * Cualquier rol puede acceder a ella.
     *
     * @return ResponseEntity con la lista de empleados.
     */
    @GetMapping("/auth/get-employees")
    @JsonView(EmployeeView.SimpleEmployee.class)
    private ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    /**
     * Obtiene todos los empleados con toda la información (Ver más en Views).
     * Solo rol admin y employee pueden acceder a ella.
     *
     * @return ResponseEntity con la lista de empleados.
     */
    @GetMapping("/auth/employees")
    private ResponseEntity<List<Employee>> getAllEmployeesWithAllInfo() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    /**
     * Obtiene un empleado por su ID. Solo rol admin y employee pueden acceder a
     * ella.
     *
     * @param id ID del empleado a obtener.
     * @return ResponseEntity con el empleado.
     */
    @GetMapping("/auth/employees/{id}")
    private ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    /**
     * Crea un nuevo empleado sin un DNI existente.
     *
     * @param request La información del empleado a crear.
     * @return ResponseEntity con el empleado creado y el código de estado 200
     * si la solicitud es exitosa, o un ResponseEntity con el código de estado
     * 400 si ocurre un error durante la creación.
     */
    @PostMapping("/auth/employees")
    private ResponseEntity<?> createEmployee(@RequestBody Employee request) {
        try {
            return ResponseEntity.ok(tokenService.registerEmployee(request, 0));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(e.getMessage());
        }
    }

    /**
     * Crea un nuevo empleado y vincula con un DNI existente.
     *
     * @param dni DNI del usuario existente.
     * @param request La información del empleado a crear.
     * @return El token generado para el empleado creado.
     */
    @PostMapping("/auth/employees/{dni}")
    private Token createEmployeeAndLinkWitExistPerson(@PathVariable int dni, @RequestBody Employee request) {
        return tokenService.registerEmployee(request, dni);
    }

    /**
     * Actualiza la información de un empleado existente.
     * @param id ID del empleado a actualizar.
     * @param request La información actualizada del empleado.
     * @return ResponseEntity con el empleado actualizado.
     */
    @PutMapping("/auth/employees/{id}")
    private ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee request) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, request));
    }

    /**
     * Elimina un empleado existente.
     * @param id ID del empleado a eliminar.
     * @param operation Operación de eliminación a realizar.
     * @return ResponseEntity con un mensaje de confirmación y el código de estado 200 si la solicitud es exitosa,
     * o un ResponseEntity con el código de estado 400 si ocurre un error durante la eliminación.
     */
    @DeleteMapping("/auth/employees/{id}")
    private ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable int id, @RequestParam int operation) {
        try {
            return ResponseEntity.ok(employeeService.deleteEmployee(id, operation));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }
    }
}
