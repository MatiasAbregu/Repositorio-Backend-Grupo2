package com.example.travsky.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "packages")
public class Package {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "p_code")
    private int code;

    @Column(name = "p_name")
    private String name;

    @OneToMany(mappedBy = "packageName")
    @JsonIgnore
    private List<ServicePackage> listServices;
}
