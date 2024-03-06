package com.example.travsky.controllers;

import com.example.travsky.models.Service;
import com.example.travsky.views.ServiceView;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.travsky.repositories.ServiceRepository;

/**
 * @author Matias
 */

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ControllerServices {

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/servicios")
    @JsonView(ServiceView.SimpleService.class)
    private ResponseEntity<List<Service>> getAllTypeServices(@RequestParam(required = false) String type) {
        if (type != null) {
            return ResponseEntity.ok(serviceRepository.findByType(Character.toUpperCase(type.charAt(0)) + type.substring(1)));
        } else {
            return ResponseEntity.ok(serviceRepository.findAll());
        }
    }

    @GetMapping("/servicios/{id}")
    @JsonView(ServiceView.DetailedService.class)
    private ResponseEntity<Service> getServiceById(@PathVariable int id) {
        return ResponseEntity.ok(serviceRepository.findById(id).orElseThrow());
    }

    @PostMapping("/servicios")
    @JsonView(ServiceView.DetailedService.class)
    private Service createOrUpdateService(@RequestBody Service request) {
        return serviceRepository.save(request);
    }

    @PutMapping("/servicios/{id}")
    @JsonView(ServiceView.DetailedService.class)
    private ResponseEntity<Service> updateService(@PathVariable int id, @RequestBody Service request) {
        Service s = serviceRepository.findById(id).orElseThrow();
        s.setName(request.getName());
        s.setDesc(request.getDesc());
        s.setDestination(request.getDestination());
        s.setDate(request.getDate());
        s.setType(request.getType());
        s.setPrice(request.getPrice());

        Service sA = serviceRepository.save(s);
        return ResponseEntity.ok(sA);
    }

    @DeleteMapping("/servicios/{id}")
    private ResponseEntity<Map<String, Boolean>> deleteService(@PathVariable int id) {
        Service s = serviceRepository.findById(id).orElseThrow();
        serviceRepository.delete(s);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }
}
