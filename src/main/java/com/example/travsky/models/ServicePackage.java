package com.example.travsky.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa el servicio asociado a un paquete.
 * Uso de lombook para ahorrar la creación de constructores y getters y setters.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "servicesxpackages")
public class ServicePackage {

    // Identificador único autoincrementable de la servicio asociado al paquete.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codesp")
    private int code;

    // Paquete al cual se asociara el servicio.
    @ManyToOne
    @JoinColumn(name = "code_pack")
    @JsonIgnore
    private Package packageName;

    // Servicio el cual se asociara.
    @ManyToOne
    @JoinColumn(name = "code_serv")
    @JsonIgnore
    private Service service;

}