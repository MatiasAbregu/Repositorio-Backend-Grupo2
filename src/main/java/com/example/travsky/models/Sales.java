package com.example.travsky.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

/**
 * Entidad que representa una venta.
 * Uso de lombook para ahorrar la creación de constructores y getters y setters.
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sales")
public class Sales {
    
    // Identificador único autoincrementable de la venta.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "v_code")
    private int code;
    
    // Fecha en la que se realizó la venta.
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private LocalDate date;
    
    // Método de pago de la venta.
    @Column(name = "payment")
    private String payment;
    
    // La venta está asociada obligatoriamente a un cliente.
    @ManyToOne
    @JoinColumn(name = "client")
    private Client client;
    
    // La venta puede estar asociada con un servicio.
    @ManyToOne
    @JoinColumn(name = "service")
    private Service service;
    
    // La venta puede estar asociada con un paquete.
    @ManyToOne
    @JoinColumn(name = "package")
    private Package packageName;
    
    // La venta está asociada obligatoriamente a un empleado.
    @ManyToOne
    @JoinColumn(name = "employee")
    private Employee employee;

}