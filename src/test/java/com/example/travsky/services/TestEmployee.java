package com.example.travsky.services;

import com.example.travsky.models.Employee;
import com.example.travsky.models.Person;
import com.example.travsky.models.Sales;
import com.example.travsky.models.User;
import com.example.travsky.repositories.EmployeeRepository;
import com.example.travsky.repositories.PersonRepository;
import com.example.travsky.repositories.SalesRepository;
import com.example.travsky.repositories.UserRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class TestEmployee {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SalesRepository salesRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void test_getAllEmployees() {
        List<Employee> listOfEmployees = Arrays.asList(new Employee(), new Employee());
        Mockito.when(employeeService.getAllEmployees()).thenReturn(listOfEmployees);

        List<Employee> list = employeeService.getAllEmployees();

        Assertions.assertEquals(list, listOfEmployees);
        Mockito.verify(employeeRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void test_getEmployeeById() {
        Employee employeeMock = new Employee();
        employeeMock.setCode(1);

        Mockito.when(employeeRepository.findById(employeeMock.getCode())).thenReturn(Optional.of(employeeMock));

        Employee employee = employeeService.getEmployeeById(employeeMock.getCode());

        Assertions.assertEquals(employee, employeeMock);
        Mockito.verify(employeeRepository, Mockito.times(1)).findById(employeeMock.getCode());
    }

    @Test
    public void test_registerEmployeeAndNewPerson() {
        Employee employeeMock = new Employee();
        Person person = new Person();
        User user = new User();

        user.setUsername("Nombre");
        user.setPassword("12345");
        user.setCellphone("Celular");
        user.setEmail("Email");
        user.setRole("Usuario");

        person.setDni(54321);
        person.setFirstName("Nombre");
        person.setLastName("Apellido");
        person.setNationality("Nacionalidad");
        person.setAddress("Direccion");
        person.setBirthdate(LocalDate.of(2004, 3, 7));
        person.setUser(user);

        employeeMock.setIncome(10);
        employeeMock.setJob("Job");
        employeeMock.setPerson(person);

        Mockito.when(personRepository.findById(person.getDni())).thenReturn(Optional.empty());
        Mockito.when(passwordEncoder.encode("12345")).thenReturn("encodedPassword");
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(personRepository.save(person)).thenReturn(person);
        Mockito.when(employeeRepository.save(employeeMock)).thenReturn(employeeMock);

        Employee employee = employeeService.registerEmployee(employeeMock, 0);
        Assertions.assertEquals(employee, employeeMock);
        Mockito.verify(passwordEncoder, Mockito.times(1)).encode("12345");
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        Mockito.verify(personRepository, Mockito.times(1)).save(person);
        Mockito.verify(employeeRepository, Mockito.times(1)).save(employeeMock);
    }

    @Test
    public void test_registerEmployeeAndLinkWithExistPerson() {
        Employee employeeMock = new Employee();
        Person person = new Person();
        User user = new User();

        user.setCode(1);
        user.setRole("User");
        person.setDni(12345);
        person.setUser(user);
        employeeMock.setPerson(person);

        Mockito.when(personRepository.findById(person.getDni())).thenReturn(Optional.of(person));
        Mockito.when(userRepository.findById(user.getCode())).thenReturn(Optional.of(user));
        Mockito.when(employeeRepository.save(employeeMock)).thenReturn(employeeMock);

        Employee employee = employeeService.registerEmployee(employeeMock, person.getDni());
        Assertions.assertNotNull(employee);
        Mockito.verify(employeeRepository, Mockito.times(1)).save(employeeMock);
    }

    @Test
    public void test_updateEmployee() {
        Employee existEmployee = new Employee();
        existEmployee.setCode(1);
        existEmployee.setIncome(20);
        Person existPerson = new Person();
        existPerson.setDni(1);
        existPerson.setAddress("DireccionUno");
        User existUser = new User();
        existUser.setCode(1);
        existUser.setRole("Employee");
        existPerson.setUser(existUser);
        existEmployee.setPerson(existPerson);

        Employee updateEmployee = new Employee();
        updateEmployee.setCode(1);
        updateEmployee.setIncome(40);
        Person updatePerson = new Person();
        updatePerson.setDni(1);
        updatePerson.setAddress("DireccionDos");
        User updateUser = new User();
        updateUser.setCode(1);
        updateUser.setRole("Admin");
        updatePerson.setUser(updateUser);
        updateEmployee.setPerson(updatePerson);

        Mockito.when(employeeRepository.findById(updateEmployee.getCode())).thenReturn(Optional.of(existEmployee));
        Mockito.when(personRepository.findById(existEmployee.getPerson().getDni())).thenReturn(Optional.of(existPerson));
        Mockito.when(userRepository.findById(existPerson.getUser().getCode())).thenReturn(Optional.of(existUser));

        Mockito.when(userRepository.save(updateUser)).thenReturn(updateUser);
        Mockito.when(personRepository.save(updatePerson)).thenReturn(updatePerson);
        Mockito.when(employeeRepository.save(updateEmployee)).thenReturn(updateEmployee);

        Employee employee = employeeService.updateEmployee(updateEmployee.getCode(), updateEmployee);
        Assertions.assertEquals(employee, updateEmployee);
        Mockito.verify(employeeRepository, Mockito.times(1)).save(updateEmployee);
        Mockito.verify(personRepository, Mockito.times(1)).save(updatePerson);
        Mockito.verify(userRepository, Mockito.times(1)).save(updateUser);
    }

    @Test
    public void test_deleteEmployeeAndPerson() {
        Employee existEmployee = new Employee();
        existEmployee.setCode(1);
        Person existPerson = new Person();
        existPerson.setDni(1);
        User existUser = new User();
        existUser.setCode(1);
        existEmployee.setPerson(existPerson);
        existPerson.setUser(existUser);

        Mockito.when(employeeRepository.findById(existEmployee.getCode())).thenReturn(Optional.of(existEmployee));
        Mockito.when(userRepository.findById(existPerson.getUser().getCode())).thenReturn(Optional.of(existUser));

        Map<String, Boolean> response = employeeService.deleteEmployee(existEmployee.getCode(), 2);

        Assertions.assertTrue(response.get("¡Datos personales del empleado eliminados con éxito!"));
        Mockito.verify(employeeRepository, Mockito.times(1)).delete(existEmployee);
        Mockito.verify(userRepository, Mockito.times(1)).delete(existUser);
    }
    
    @Test
    public void test_deleteEmployeeAndHisSales(){
        Employee existEmployee = new Employee();
        existEmployee.setCode(1);
        Person existPerson = new Person();
        existPerson.setDni(1);
        User existUser = new User();
        existUser.setCode(1);
        existEmployee.setPerson(existPerson);
        existPerson.setUser(existUser);
        List<Sales> sales = new ArrayList<>();
        sales.add(new Sales());
        
        Mockito.when(employeeRepository.findById(existEmployee.getCode())).thenReturn(Optional.of(existEmployee));
        Mockito.when(salesRepository.findAllByEmployee(existEmployee)).thenReturn(sales);
        Mockito.when(userRepository.findById(existUser.getCode())).thenReturn(Optional.of(existUser));
        
        Map<String, Boolean> response = employeeService.deleteEmployee(existEmployee.getCode(), 1);
        
        Assertions.assertTrue(response.get("¡Datos de ventas y del empleado eliminados con éxito! ¡Usuario además cambiado con éxito!"));
        Mockito.verify(salesRepository, Mockito.times(1)).deleteById(ArgumentMatchers.any());
        Mockito.verify(employeeRepository, Mockito.times(1)).deleteById(existEmployee.getCode());
        Assertions.assertEquals("User", existUser.getRole());
        Mockito.verify(userRepository, Mockito.times(1)).save(existUser); 
    }
    
}