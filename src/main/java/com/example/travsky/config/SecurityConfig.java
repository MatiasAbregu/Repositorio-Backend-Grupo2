package com.example.travsky.config;

import com.example.travsky.jwt.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Clase en la que se define reglas de autorización y configuración de autenticación utilizando Spring Security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;
    
    @Autowired
    private AuthenticationProvider authProvider;
    
     /**
     * Configura las reglas de seguridad para diferentes rutas de la aplicación, desactivación del cross site request forgery y diferentes autenticaciones.
     * Todo a través de JWT.
     * @param http: Objeto HttpSecurity para configurar la seguridad de la aplicación.
     * @return SecurityFilterChain: Objeto que retornará.
     * @throws Exception: Por si hay algún error al momento de configurar la seguridad.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authRequest 
                        -> authRequest.requestMatchers("/auth/services/**").hasAnyAuthority("Admin", "Employee").
                                requestMatchers("/auth/employees/**").hasAuthority("Admin").
                                requestMatchers("/auth/get-employee").hasAnyAuthority("Admin", "Employee", "User").
                                requestMatchers("/auth/packages/**").hasAnyAuthority("Admin", "Employee").
                                requestMatchers("/auth/sales/**").hasAnyAuthority("Admin", "Employee").
                                requestMatchers("/auth/clients/**").hasAnyAuthority("Admin", "Employee")
                        .anyRequest().permitAll())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}