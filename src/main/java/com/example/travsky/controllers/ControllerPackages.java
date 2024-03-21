package com.example.travsky.controllers;

import com.example.travsky.dto.packageWithServices;
import com.example.travsky.models.Package;
import com.example.travsky.models.Service;
import com.example.travsky.models.ServicePackage;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.travsky.services.PackageService;
import org.springframework.http.HttpStatusCode;

/**
 * @author Matias
 */
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ControllerPackages {

    @Autowired
    private PackageService packageService;

    @GetMapping("/packages")
    private ResponseEntity<List<packageWithServices>> getAllPackages() {
        return ResponseEntity.ok(packageService.getAllPackages());
    }

    @GetMapping("/packages/{id}")
    private ResponseEntity<packageWithServices> getPackageById(@PathVariable int id) {
        return ResponseEntity.ok(packageService.getPackageById(id));
    }

    @PostMapping("/auth/packages")
    private ResponseEntity<Package> createPackage(@RequestBody packageWithServices request) {
        try {
            return ResponseEntity.ok(packageService.createPackage(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }
    }

    @PutMapping("/auth/packages/{id}")
    private ResponseEntity<Package> updatePackage(@PathVariable int id, @RequestBody packageWithServices request) {
        return ResponseEntity.ok(packageService.updatePackage(id, request));
    }

    @DeleteMapping("/auth/packages/{id}")
    private ResponseEntity<Map<String, Boolean>> deletePackage(@PathVariable int id) {
        return ResponseEntity.ok(packageService.deletePackage(id));
    }
}
