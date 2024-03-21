package com.example.travsky.dto;

import com.example.travsky.models.Service;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que representa un paquete con sus servicios asociados.
 * Su función es lograr manipular ServicePackage y Package en una solicitud.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class packageWithServices {
    
    // El paquete a usar.
    private com.example.travsky.models.Package packageInfo;
    
    // Los servicios asociados al paquete.
    private List<Service> services;
}
