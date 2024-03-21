package com.example.travsky.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un token.
 * Uso de lombook para ahorrar la creación de constructores y getters y setters.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    
    // Valor que tendrá el token.
    private String value;
}
