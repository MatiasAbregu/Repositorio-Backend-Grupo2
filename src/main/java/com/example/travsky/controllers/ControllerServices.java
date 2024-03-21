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
@CrossOrigin(origins = "http://localhost:5173")
public class ControllerServices {  
    
    @Autowired
    private ServiceService serviceService;
    
    @GetMapping("/services")
    @JsonView(ServiceView.SimpleService.class)
    private ResponseEntity<List<Service>> getAllTypeServices(@RequestParam(required = false) String type) {
        return ResponseEntity.ok(serviceService.getServicesByType(type));
    }
    
    @GetMapping("/auth/services-detailed")
    @JsonView(ServiceView.DetailedService.class)
    private ResponseEntity<List<Service>> getDetailedServices() {
        return ResponseEntity.ok(serviceService.getDetailedServices());
    }
    
    @GetMapping("/services/{id}")
    @JsonView(ServiceView.DetailedService.class)
    private ResponseEntity<Service> getServiceById(@PathVariable int id) {
        return ResponseEntity.ok(serviceService.findServiceById(id));
    }
    
    @PostMapping("/auth/services")
    @JsonView(ServiceView.DetailedService.class)
    private ResponseEntity<Service> createService(@RequestBody Service request) {
        return ResponseEntity.ok(serviceService.createService(request));
    }
    
    @PutMapping("/auth/services/{id}")
    @JsonView(ServiceView.DetailedService.class)
    private ResponseEntity<Service> updateService(@PathVariable int id, @RequestBody Service request) {
        return ResponseEntity.ok(serviceService.updateService(id, request));
    }
    
    @DeleteMapping("/auth/services/{id}")
    private ResponseEntity<Map<String, Boolean>> deleteService(@PathVariable int id) {
        return ResponseEntity.ok(serviceService.deleteService(id));
    }
}
