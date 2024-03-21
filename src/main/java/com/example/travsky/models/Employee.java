package com.example.travsky.models;

import com.example.travsky.views.EmployeeView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa a un empleado.
 * Uso de lombook para ahorrar la creación de constructores y getters y setters.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "employees")
public class Employee {

    // Identificador único autoincrementable del empleado.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "e_code")
    @JsonView({EmployeeView.SimpleEmployee.class, EmployeeView.DetailedEmployee.class})
    private int code;

    // El empleado está asociado obligatoriamente a una persona.
    @OneToOne
    @JoinColumn(name = "e_dni")
    @JsonView({EmployeeView.SimpleEmployee.class, EmployeeView.DetailedEmployee.class})
    private Person person;

    // El trabajo del cual se ocupa.
    @Column(name = "job")
    @JsonView({EmployeeView.DetailedEmployee.class})
    private String job;

    // El ingreso que recibe el empleado.
    @Column(name = "income")
    @JsonView({EmployeeView.DetailedEmployee.class})
    private int income;

}