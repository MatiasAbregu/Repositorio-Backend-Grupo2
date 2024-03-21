package com.example.travsky.models;

import com.example.travsky.views.EmployeeView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author Matias
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "employees")
public class Employee {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "e_code")
    @JsonView({EmployeeView.SimpleEmployee.class, EmployeeView.DetailedEmployee.class})
    private int code;

    @OneToOne
    @JoinColumn(name = "e_dni")
    @JsonView({EmployeeView.SimpleEmployee.class, EmployeeView.DetailedEmployee.class})
    private Person person;

    @Column(name = "job")
    @JsonView({EmployeeView.DetailedEmployee.class})
    private String job;

    @Column(name = "income")
    @JsonView({EmployeeView.DetailedEmployee.class})
    private int income;

}