package com.example.travsky.config;

import com.example.travsky.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuración de la aplicación. Esta clase define la configuración de autenticación y gestión de usuarios utilizando Spring Security.
 */
@Configuration
public class ApplicationConfig {
    
    @Autowired
    private UserRepository userRepository;
    
    /**
     * Configuración del administrador de autenticación.
     * @param config Configuración de autenticación proporcionada por Spring.
     * @return AuthenticationManager configurado.
     * @throws Exception Si hay algún error al obtener el AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
 
    /**
     * Configuración de la autenticación personalizada.
     * Estableciendo en el Dao el userDetailsService y el passwordEncoder.
     * @return AuthenticationProvider configurado.
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Configura el codificador de contraseñas.
     * @return PasswordEncoder configurado.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * Configuración del servicio de detalles de usuario.
     * @return UserDetailsService configurado.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado."));
    }

}