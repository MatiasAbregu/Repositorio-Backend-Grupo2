package com.example.travsky.models;

import com.example.travsky.views.EmployeeView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

/**
 * @author Matias
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "person")
public class Person {
 
    @Id
    @Column(name = "dni")
    @JsonView({EmployeeView.SimpleEmployee.class, EmployeeView.DetailedEmployee.class})
    private int dni;
    
    @Column(name = "first_name")
    @JsonView({EmployeeView.SimpleEmployee.class, EmployeeView.DetailedEmployee.class})
    private String firstName;
    
    @Column(name = "last_name")
    @JsonView({EmployeeView.SimpleEmployee.class, EmployeeView.DetailedEmployee.class})
    private String lastName;
    
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    @JsonView({EmployeeView.DetailedEmployee.class})
    private LocalDate birthdate;
    
    @Column(name = "address")
    @JsonView({EmployeeView.DetailedEmployee.class})
    private String address;
    
    @Column(name = "nationality")
    @JsonView({EmployeeView.DetailedEmployee.class})
    private String nationality;
    
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "user", referencedColumnName = "u_code")
    @JsonView({EmployeeView.DetailedEmployee.class})
    private User user;

}