package com.example.travsky.services;

import com.example.travsky.models.Person;
import com.example.travsky.models.Token;
import com.example.travsky.models.User;
import com.example.travsky.repositories.PersonRepository;
import com.example.travsky.repositories.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class TestUser {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authManager;

    @InjectMocks
    private UserService userService;

    @Test
    public void testLogin_ValidUser() {
        User user = new User();
        Person person = new Person();
        user.setUsername("Admin");
        user.setPassword("patito12345");

        Mockito.when(userRepository.findAllByUsername(user.getUsername())).thenReturn(List.of(user));
        Mockito.when(personRepository.findByUser(user)).thenReturn(Optional.of(person));
        Mockito.when(jwtService.getToken(user, person)).thenReturn("mockedToken");

        Token token = userService.login(user);

        Assertions.assertEquals("mockedToken", token.getValue());
    }

    @Test
    public void testLogin_InvalidUser() {
        User user = new User();
        Person person = new Person();
        user.setUsername("Admin");
        user.setPassword("patito123456");

        Mockito.when(userRepository.findAllByUsername(user.getUsername())).thenReturn(List.of(user));
        Mockito.when(authManager.authenticate(ArgumentMatchers.any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Usuario o contraseña incorrectos"));

        UsernameNotFoundException ex = Assertions.assertThrows(UsernameNotFoundException.class, () -> userService.login(user));

        Assertions.assertEquals("Usuario o contraseña incorrectos", ex.getMessage());
    }

    @Test
    public void testRegister_ValidUser() {
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

        Mockito.when(personRepository.findById(person.getDni())).thenReturn(Optional.empty());
        Mockito.when(passwordEncoder.encode("12345")).thenReturn("encodedPassword");
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(personRepository.save(person)).thenReturn(person);

        Person personCreated = userService.register(person);
        Assertions.assertEquals(person, personCreated);
        Mockito.verify(passwordEncoder, Mockito.times(1)).encode("12345");
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        Mockito.verify(personRepository, Mockito.times(1)).save(person);
    }

    @Test
    public void testRegister_ExistUser() {
        Person person = new Person();
        User user = new User();

        user.setUsername("Nombre");
        user.setPassword("12345");
        user.setCellphone("Celular");
        user.setEmail("Email");
        user.setRole("Usuario");
        
        person.setDni(12345678);
        person.setFirstName("Nombre");
        person.setLastName("Apellido");
        person.setNationality("Nacionalidad");
        person.setAddress("Direccion");
        person.setBirthdate(LocalDate.MIN);
        person.setUser(user);

        Mockito.when(personRepository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(person));
        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> userService.register(person));
        
        Assertions.assertEquals("El dni ya está asociado a una persona.", ex.getMessage());
    }
    
}