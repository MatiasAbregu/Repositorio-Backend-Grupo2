package com.example.travsky.models;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Matias
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "clients")
public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_code")
    private int code;
    
    @OneToOne
    @JoinColumn(name = "c_dni")
    private Person person;
    
}