package com.example.travsky.models;

import jakarta.persistence.*;
import java.util.Date;
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
    private int dni;
    
    @Column(name = "first_name")
    private String fistName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "nationality")
    private String nationality;
    
    @OneToOne
    @JoinColumn(name = "user")
    private User user;

}