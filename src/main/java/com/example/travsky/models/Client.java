package com.example.travsky.models;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa a un cliente.
 * Uso de lombook para ahorrar la creación de constructores y getters y setters.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "clients")
public class Client {
    
    // Identificador único autoincrementable del cliente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_code")
    private int code;
    
    // El cliente está asociado por una persona obligatoriamente
    @OneToOne
    @JoinColumn(name = "c_dni")
    private Person person;
    
}