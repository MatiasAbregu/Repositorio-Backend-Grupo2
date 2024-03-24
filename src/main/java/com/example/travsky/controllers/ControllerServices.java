package com.example.travsky.controllers;

import com.example.travsky.models.Service;
import com.example.travsky.views.ServiceView;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.travsky.services.ServiceService;

/**
 * @author Matias
 */
@RestController
public class ControllerServices {  
    
    @Autowired
    private ServiceService serviceService;
    
    /**
     * Obtiene todos los servicios filtrados opcionalmente por tipo.
     * Tienen acceso a ella todos los roles.
     * @param type Tipo opcional para filtrar los servicios.
     * @return ResponseEntity con la lista de servicios.
     */
    @GetMapping("/services")
    @JsonView(ServiceView.SimpleService.class)
    private ResponseEntity<List<Service>> getAllTypeServices(@RequestParam(required = false) String type) {
        return ResponseEntity.ok(serviceService.getServicesByType(type));
    }
    
    /**
     * Obtiene todos los servicios detallados.
     * Tienen acceso a ella los roles admin y employee.
     * @return ResponseEntity con la lista de servicios detallados.
     */
    @GetMapping("/auth/services-detailed")
    @JsonView(ServiceView.DetailedService.class)
    private ResponseEntity<List<Service>> getDetailedServices() {
        return ResponseEntity.ok(serviceService.getDetailedServices());
    }
    
    /**
     * Obtiene un servicio por su ID.
     * @param id ID del servicio a obtener.
     * @return ResponseEntity con el servicio.
     */
    @GetMapping("/services/{id}")
    @JsonView(ServiceView.DetailedService.class)
    private ResponseEntity<Service> getServiceById(@PathVariable int id) {
        return ResponseEntity.ok(serviceService.findServiceById(id));
    }
    
    /**
     * Crea un nuevo servicio.
     * @param request La información del servicio a crear.
     * @return ResponseEntity con el servicio creado.
     */
    @PostMapping("/auth/services")
    @JsonView(ServiceView.DetailedService.class)
    private ResponseEntity<Service> createService(@RequestBody Service request) {
        return ResponseEntity.ok(serviceService.createService(request));
    }
    
    /**
     * Actualiza la información de un servicio existente.
     * @param id ID del servicio a actualizar.
     * @param request La información actualizada del servicio.
     * @return ResponseEntity con el servicio actualizado.
     */
    @PutMapping("/auth/services/{id}")
    @JsonView(ServiceView.DetailedService.class)
    private ResponseEntity<Service> updateService(@PathVariable int id, @RequestBody Service request) {
        return ResponseEntity.ok(serviceService.updateService(id, request));
    }
    
    /**
     * Elimina un servicio existente.
     * @param id ID del servicio a eliminar.
     * @return ResponseEntity con un mensaje de confirmación.
     */
    @DeleteMapping("/auth/services/{id}")
    private ResponseEntity<Map<String, Boolean>> deleteService(@PathVariable int id) {
        return ResponseEntity.ok(serviceService.deleteService(id));
    }
}
