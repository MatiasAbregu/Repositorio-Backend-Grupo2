package com.example.travsky.models;

import com.example.travsky.views.EmployeeView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

/**
 * Entidad que representa a una persona.
 * Uso de lombook para ahorrar la creación de constructores y getters y setters.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "person")
public class Person {
 
    // Identificador que se le asigna a la persona, utilizando el DNI de la misma.
    @Id
    @Column(name = "dni")
    @JsonView({EmployeeView.SimpleEmployee.class, EmployeeView.DetailedEmployee.class})
    private int dni;
    
    // Nombre de la persona.
    @Column(name = "first_name")
    @JsonView({EmployeeView.SimpleEmployee.class, EmployeeView.DetailedEmployee.class})
    private String firstName;
    
    // Apellido de la persona.
    @Column(name = "last_name")
    @JsonView({EmployeeView.SimpleEmployee.class, EmployeeView.DetailedEmployee.class})
    private String lastName;
    
    // Fecha de nacimiento de la persona.
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    @JsonView({EmployeeView.DetailedEmployee.class})
    private LocalDate birthdate;
    
    // Dirección dónde vive.
    @Column(name = "address")
    @JsonView({EmployeeView.DetailedEmployee.class})
    private String address;
    
    // País de origen o de residencia.
    @Column(name = "nationality")
    @JsonView({EmployeeView.DetailedEmployee.class})
    private String nationality;
    
    // La persona está asociada obligatoriamente a un usuario.
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "user", referencedColumnName = "u_code")
    @JsonView({EmployeeView.DetailedEmployee.class})
    private User user;

}