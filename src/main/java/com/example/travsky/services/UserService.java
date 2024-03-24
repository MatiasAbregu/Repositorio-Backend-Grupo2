package com.example.travsky.services;

import com.example.travsky.models.Person;
import com.example.travsky.models.Token;
import com.example.travsky.models.User;
import com.example.travsky.repositories.EmployeeRepository;
import com.example.travsky.repositories.PersonRepository;
import com.example.travsky.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para la gestión de registro e inicio de sesión en la aplicación.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    /**
     * Método para iniciar sesión de un usuario.
     *
     * @param request Los detalles de inicio de sesión del usuario, incluido el
     * nombre de usuario y la contraseña.
     * @return Token JWT generado si el inicio de sesión es exitoso.
     * @throws UsernameNotFoundException Si el nombre de usuario o la contraseña
     * proporcionados son incorrectos.
     */
    public Token login(User request) {
        List<User> usersFound = userRepository.findAllByUsername(request.getUsername());
        String token;

        for (User user : usersFound) {
            try {
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), request.getPassword()));
                Person person = personRepository.findByUser(user).orElseThrow();
                token = jwtService.getToken(user, person);
                return new Token(token);
            } catch (AuthenticationException e) {
            }
        }
        throw new UsernameNotFoundException("Usuario o contraseña incorrectos");
    }

    /**
     * Registra a una persona en el sistema, con un DNI irrepetible sino tira error.
     *
     * @param request Información de la persona a registrar.
     * @return El token de acceso generado para la persona registrada.
     * @throws RuntimeException Si la persona o el usuario ya existen en el
     * sistema.
     */
    @Transactional
    public Person register(Person request) {
        Person p = personRepository.findById(request.getDni()).orElse(null);

        if (p == null) {
            request.getUser().setPassword(passwordEncoder.encode(request.getUser().getPassword()));
            request.getUser().setRole("User");

            User uA = userRepository.save(request.getUser());
            request.setUser(uA);

            Person pCreated = personRepository.save(request);
            return pCreated;
        } else {
            throw new RuntimeException("El dni ya está asociado a una persona.");
        }
    }

}
