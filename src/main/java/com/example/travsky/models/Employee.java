package com.example.travsky.models;

import jakarta.persistence.*;
import java.util.List;
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
    private int code;

    @OneToOne
    @JoinColumn(name = "e_dni")
    private Person person;

    @Column(name = "job")
    private String job;

    @Column(name = "income")
    private int income;

    @OneToMany(mappedBy = "employee")
    private List<Sales> salesList;

}