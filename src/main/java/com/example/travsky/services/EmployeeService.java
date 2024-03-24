package com.example.travsky.services;

import com.example.travsky.models.Employee;
import com.example.travsky.models.Person;
import com.example.travsky.models.Sales;
import com.example.travsky.models.User;
import com.example.travsky.repositories.EmployeeRepository;
import com.example.travsky.repositories.PersonRepository;
import com.example.travsky.repositories.SalesRepository;
import com.example.travsky.repositories.UserRepository;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para la gestión de empleados en la aplicación.
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Obtiene todos los empleados registrados en la base de datos.
     *
     * @return Lista de todos los empleados.
     */
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Obtiene un empleado por su ID.
     *
     * @param id ID del empleado a obtener.
     * @return El empleado encontrado.
     * @throws RuntimeException si el empleado no existe.
     */
    public Employee getEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        return employee;
    }

    /**
     * Registra a un empleado en el sistema.
     *
     * @param request Información del empleado a registrar.
     * @param dni DNI, en caso de recibir, de una persona ya existente en el
     * sistema la cual se quiere asociar al nuevo empleado.
     * @return El empleado creado en el sistema.
     * @throws RuntimeException Si la persona asociada al empleado ya existe en
     * el sistema.
     */
    @Transactional
    public Employee registerEmployee(Employee request, int dni) {
        if (dni == 0) {
            Person pV = personRepository.findById(request.getPerson().getDni()).orElse(null);

            if (pV == null) {
                request.getPerson().getUser().setPassword(passwordEncoder.encode(request.getPerson().getUser().getPassword()));

                User uA = userRepository.save(request.getPerson().getUser());
                request.getPerson().setUser(uA);

                Person pA = personRepository.save(request.getPerson());
                request.setPerson(pA);

                Employee eCreated = employeeRepository.save(request);
                return eCreated;
            } else {
                throw new RuntimeException("Ese DNI ya está asociado a una persona.");
            }
        } else {
            Person p = personRepository.findById(dni).orElseThrow();
            User u = userRepository.findById(p.getUser().getCode()).orElseThrow();

            u.setRole(request.getPerson().getUser().getRole());
            User uA = userRepository.save(u);

            p.setUser(uA);
            request.setPerson(p);

            Employee eCreated = employeeRepository.save(request);
            return eCreated;
        }
    }

    /**
     * Actualiza la información de un empleado existente.
     *
     * @param id ID del empleado a actualizar.
     * @param request La información actualizada del empleado.
     * @return El empleado actualizado.
     */
    @Transactional
    public Employee updateEmployee(int id, Employee request) {
        Employee e = employeeRepository.findById(id).orElseThrow();
        Person p = personRepository.findById(e.getPerson().getDni()).orElseThrow();
        User u = userRepository.findById(p.getUser().getCode()).orElseThrow();

        e.setJob(request.getJob());
        e.setIncome(request.getIncome());

        p.setFirstName(request.getPerson().getFirstName());
        p.setLastName(request.getPerson().getLastName());
        p.setBirthdate(request.getPerson().getBirthdate());
        p.setAddress(request.getPerson().getAddress());
        p.setNationality(request.getPerson().getNationality());

        u.setUsername(request.getPerson().getUser().getUsername());
        u.setPassword(passwordEncoder.encode(request.getPerson().getUser().getPassword()));
        u.setEmail(request.getPerson().getUser().getEmail());
        u.setCellphone(request.getPerson().getUser().getCellphone());
        u.setRole(request.getPerson().getUser().getRole());

        User uUpdated = userRepository.save(u);
        p.setUser(uUpdated);

        Person pUpdated = personRepository.save(p);
        e.setPerson(pUpdated);

        Employee eUpdated = employeeRepository.save(e);
        return eUpdated;
    }

    /**
     * Elimina un empleado existente.
     *
     * @param id ID del empleado a eliminar.
     * @param operation Operación a realizar para la eliminación (1 o 2). 1. Se
     * eliminará el empleado y sus ventas dejando su usuario con rol "User". 2.
     * Se eliminará el empleado y su usuario.
     * @return Un mensaje de confirmación y un indicador de éxito.
     */
    @Transactional
    public Map<String, Boolean> deleteEmployee(int id, int operation) {
        Map<String, Boolean> response = new HashMap<>();

        if (operation == 1) {
            Employee e = employeeRepository.findById(id).orElseThrow();
            List<Sales> s = salesRepository.findAllByEmployee(e);
            User u = userRepository.findById(e.getPerson().getUser().getCode()).orElseThrow();

            if (s != null) {
                for (Sales sT : s) {
                    salesRepository.deleteById(sT.getCode());

                }
            }
            employeeRepository.deleteById(id);
            u.setRole("User");
            userRepository.save(u);

            response.put("¡Datos de ventas y del empleado eliminados con éxito! ¡Usuario además cambiado con éxito!", Boolean.TRUE);
        } else if (operation == 2) {
            Employee e = employeeRepository.findById(id).orElseThrow();
            User u = userRepository.findById(e.getPerson().getUser().getCode()).orElseThrow();

            employeeRepository.delete(e);
            userRepository.delete(u);

            response.put("¡Datos personales del empleado eliminados con éxito!", Boolean.TRUE);
        } else {
            response.put("Operación seleccionada incorrecta.", Boolean.TRUE);
        }

        return response;
    }
}
