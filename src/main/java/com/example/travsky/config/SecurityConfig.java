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
 * @author Matias
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;
    
    @Autowired
    private AuthenticationProvider authProvider;
    
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