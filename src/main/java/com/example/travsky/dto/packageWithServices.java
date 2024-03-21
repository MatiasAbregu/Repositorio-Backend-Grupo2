package com.example.travsky.dto;

import com.example.travsky.models.Service;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author matia
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class packageWithServices {
    
    private com.example.travsky.models.Package packageInfo;
    private List<Service> services;
}
