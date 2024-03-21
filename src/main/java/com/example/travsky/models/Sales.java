package com.example.travsky.models;

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
@Table(name = "sales")
public class Sales {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "v_code")
    private int code;
    
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private LocalDate date;
    
    @Column(name = "payment")
    private String payment;
    
    @ManyToOne
    @JoinColumn(name = "client")
    private Client client;
    
    @ManyToOne
    @JoinColumn(name = "service")
    private Service service;
    
    @ManyToOne
    @JoinColumn(name = "package")
    private Package packageName;
    
    @ManyToOne
    @JoinColumn(name = "employee")
    private Employee employee;

}