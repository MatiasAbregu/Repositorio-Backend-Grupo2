package com.example.travsky.models;

import com.example.travsky.views.ServiceView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import java.util.*;
import lombok.*;

/**
 * @author Matias
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "services")
public class Service {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "s_code")
    @JsonView({ServiceView.SimpleService.class, ServiceView.DetailedService.class})
    private int code;
    
    @Column(name = "s_name")
    @JsonView({ServiceView.SimpleService.class, ServiceView.DetailedService.class})
    private String name;
    
    @Column(name = "s_desc")
    @JsonView({ServiceView.SimpleService.class, ServiceView.DetailedService.class})
    private String desc;
    
    @Column(name = "img")
    @JsonView({ServiceView.SimpleService.class, ServiceView.DetailedService.class})
    private String img;
    
    @Column(name = "destination")
    @JsonView({ServiceView.DetailedService.class})
    private String destination;
    
    @Column(name = "date")
    @JsonView({ServiceView.DetailedService.class})
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @Column(name = "type")
    @JsonView({ServiceView.SimpleService.class, ServiceView.DetailedService.class})
    private String type;
    
    @Column(name = "price")
    @JsonView({ServiceView.SimpleService.class})
    private float price;

    @OneToMany(mappedBy = "service")
    @JsonIgnore
    private List<ServicePackage> listPackage;
    
    
}