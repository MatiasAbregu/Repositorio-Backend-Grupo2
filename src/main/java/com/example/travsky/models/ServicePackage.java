package com.example.travsky.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author Matias
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "servicesxpackages")
public class ServicePackage {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codesp")
    private int code;
    
    @ManyToOne
    @JoinColumn(name = "code_pack")
    @JsonIgnore
    private Package packageName;
    
    @ManyToOne
    @JoinColumn(name = "code_serv")
    @JsonIgnore
    private Service service;
 
}