package com.example.travsky.models;

import com.example.travsky.views.ServiceView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

/**
 * Entidad que representa un servicio.
 * Uso de lombook para ahorrar la creación de constructores y getters y setters.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "services")
public class Service {
    
    // Identificador único autoincrementable del servicio.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "s_code")
    @JsonView({ServiceView.SimpleService.class, ServiceView.DetailedService.class})
    private int code;
    
    // Nombre del servicio.
    @Column(name = "s_name")
    @JsonView({ServiceView.SimpleService.class, ServiceView.DetailedService.class})
    private String name;
    
    // Descripción del servicio.
    @Column(name = "s_desc")
    @JsonView({ServiceView.SimpleService.class, ServiceView.DetailedService.class})
    private String desc;
    
    // Imagen que se asocia a una URL por eso es un string.
    @Column(name = "img")
    @JsonView({ServiceView.SimpleService.class, ServiceView.DetailedService.class})
    private String img;
    
    // Destino del servicio.
    @Column(name = "destination")
    @JsonView({ServiceView.DetailedService.class})
    private String destination;
    
    // Fecha en la que se realizará o se podrá usar el servicio.
    @Column(name = "date")
    @JsonView({ServiceView.DetailedService.class})
    @Temporal(TemporalType.DATE)
    private LocalDate date;
    
    // Tipo de servicio.
    @Column(name = "type")
    @JsonView({ServiceView.SimpleService.class, ServiceView.DetailedService.class})
    private String type;
    
    // Precio del servicio.
    @Column(name = "price")
    @JsonView({ServiceView.SimpleService.class, ServiceView.DetailedService.class})
    private float price;
    
    // Variable extra usada para poder gestionar servicios en ServicePackage.
    @Transient
    private int idSxP;
}