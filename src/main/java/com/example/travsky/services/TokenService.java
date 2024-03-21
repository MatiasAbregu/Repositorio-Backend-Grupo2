package com.example.travsky.services;

import com.example.travsky.models.Employee;
import com.example.travsky.models.Person;
import com.example.travsky.models.Token;
import com.example.travsky.models.User;
import com.example.travsky.repositories.EmployeeRepository;
import com.example.travsky.repositories.PersonRepository;
import com.example.travsky.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para la gestión de registro e inicio de sesión en la aplicación.
 */
@Service
public class TokenService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    /**
     * Autentica a un usuario y genera un token de acceso.
     * @param request Datos de usuario para la autenticación.
     * @return El token de acceso generado.
     * @throws RuntimeException Si la autenticación falla o el usuario no existe.
     */
    public Token login(User request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        Person person = personRepository.findByUser(user).orElseThrow();
        String token = jwtService.getToken(user, person);
        return new Token(token);
    }

    /**
     * Registra a una persona en el sistema, con un DNI y un username irrepetible sino tira error.
     * @param request Información de la persona a registrar.
     * @return El token de acceso generado para la persona registrada.
     * @throws RuntimeException Si la persona o el usuario ya existen en el sistema.
     */
    @Transactional
    public Token register(Person request) {
        Person p = personRepository.findById(request.getDni()).orElse(null);
        User u = userRepository.findByUsername(request.getUser().getUsername()).orElse(null);
        Token t = new Token();

        if (p == null) {
            if (u == null) {
                request.getUser().setPassword(passwordEncoder.encode(request.getUser().getPassword()));
                request.getUser().setRole("User");

                User uA = userRepository.save(request.getUser());
                request.setUser(uA);

                personRepository.save(request);
                t.setValue(jwtService.getToken(request.getUser(), request));
                return t;
            } else {
                throw new RuntimeException("El usuario ya existe.");
            }
        } else {
            throw new RuntimeException("El dni ya está asociado a una persona");
        }
    }

    /**
     * Registra a un empleado en el sistema.
     * @param request Información del empleado a registrar.
     * @param dni DNI, en caso de recibir, de una persona ya existente en el sistema la cual se quiere asociar al nuevo empleado.
     * @return El token de acceso generado para el empleado registrado.
     * @throws RuntimeException Si la persona asociada al empleado ya existe en el sistema.
     */
    @Transactional
    public Token registerEmployee(Employee request, int dni) {
        Token t = new Token();
        if (dni == 0) {
            Person pV = personRepository.findById(request.getPerson().getDni()).orElse(null);
            User uV = userRepository.findByUsername(request.getPerson().getUser().getUsername()).orElse(null);
            
            if (pV == null) {
                if (uV == null) {
                    request.getPerson().getUser().setPassword(passwordEncoder.encode(request.getPerson().getUser().getPassword()));

                    User uA = userRepository.save(request.getPerson().getUser());
                    request.getPerson().setUser(uA);

                    Person pA = personRepository.save(request.getPerson());
                    request.setPerson(pA);

                    employeeRepository.save(request);
                } else {
                    throw new RuntimeException("Ese usuario ya existe.");
                }
            } else {
                throw new RuntimeException("Ese DNI ya está asociado a una persona.");
            }

            t.setValue(jwtService.getToken(request.getPerson().getUser(), request.getPerson()));
            return t;
        } else {
            Person p = personRepository.findById(dni).orElseThrow();
            User u = userRepository.findById(p.getUser().getCode()).orElseThrow();

            u.setRole(request.getPerson().getUser().getRole());
            User uA = userRepository.save(u);

            p.setUser(uA);
            request.setPerson(p);

            employeeRepository.save(request);
            t.setValue(jwtService.getToken(request.getPerson().getUser(), p));
            return t;
        }
    }

}