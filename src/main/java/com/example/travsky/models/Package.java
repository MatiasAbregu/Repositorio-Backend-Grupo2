package com.example.travsky.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

/**
 * Entidad que representa a un paquete.
 * Uso de lombook para ahorrar la creación de constructores y getters y setters.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "packages")
public class Package {

    // Identificador único autoincrementable del paquete.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "p_code")
    private int code;

    // Nombre que recibe el paquete.
    @Column(name = "p_name")
    private String name;

    // Lista que no se incluye en el JSON y muestra todos los servicios que tiene asociado en la entidad ServicePackage.
    @OneToMany(mappedBy = "packageName")
    @JsonIgnore
    private List<ServicePackage> listServices;
}
