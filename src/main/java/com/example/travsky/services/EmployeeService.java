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
 * @author matia
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
    
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    
    public Employee getEmployeeById(int id){
        Employee employee = employeeRepository.findById(id).orElseThrow();
        return employee;
    }
    
    @Transactional
    public Employee updateEmployee(int id, Employee request){
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
    
    @Transactional
    public Map<String, Boolean> deleteEmployee(int id, int operation){
        Map<String, Boolean> response = new HashMap<>();
        
        if (operation == 1) {
            Employee e = employeeRepository.findById(id).orElseThrow();
            List<Sales> s = salesRepository.findAllById(Collections.singletonList(id));
            User u = userRepository.findById(e.getPerson().getUser().getCode()).orElseThrow();

            if (s != null) {
                for (Sales sT : s) {
                    if (sT.getClient().getCode() != e.getCode()) {
                        salesRepository.deleteById(sT.getCode());
                    }
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