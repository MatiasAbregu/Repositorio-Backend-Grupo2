package com.example.travsky.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author matia
 */

@Configuration
public class CorsConfig {
    
    /**
     * Configura CORS de forma global para permitir solicitudes desde diferentes orígenes.
     * @return WebMvcConfigurer - Configurador de Spring MVC para CORS.
     */
    @Bean
    public WebMvcConfigurer corsConfiguration(){
        return new WebMvcConfigurer(){
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**")
                        .allowedOrigins("http://0.0.0.0", "http://localhost:5173", 
                                "http://149.50.137.162:5173", "http://vps-3991849-x.dattaweb.com:5173") // Orígenes permitidos
                        .allowedMethods("*") // Métodos HTTP permitidos
                        .allowedHeaders("*") // Encabezados permitidos
                        .allowCredentials(true); // Permitir credenciales
            }
        };
    }
}