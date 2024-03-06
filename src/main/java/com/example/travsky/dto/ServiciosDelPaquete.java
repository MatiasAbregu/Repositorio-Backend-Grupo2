package com.example.travsky.dto;

import com.example.travsky.models.Service;
import java.util.List;
import lombok.*;

/**
 * @author Matias
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiciosDelPaquete {
    
    private String name;
    private List<Service> service;
    
}