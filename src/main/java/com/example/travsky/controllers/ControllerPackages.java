package com.example.travsky.controllers;

import com.example.travsky.dto.packageWithServices;
import com.example.travsky.models.Package;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.travsky.services.PackageService;
import org.springframework.http.HttpStatusCode;

/**
 * Controlador para la gestión de paquetes en la aplicación.
 */
@RestController
public class ControllerPackages {

    @Autowired
    private PackageService packageService;

    /**
     * Obtiene todos los paquetes con sus servicios asociados.
     * @return ResponseEntity con la lista de paquetes.
     */
    @GetMapping("/packages")
    private ResponseEntity<List<packageWithServices>> getAllPackages() {
        return ResponseEntity.ok(packageService.getAllPackages());
    }

    /**
     * Obtiene un paquete por su ID con sus servicios asociados.
     * @param id ID del paquete a obtener.
     * @return ResponseEntity con el paquete y sus servicios asociados.
     */
    @GetMapping("/packages/{id}")
    private ResponseEntity<packageWithServices> getPackageById(@PathVariable int id) {
        return ResponseEntity.ok(packageService.getPackageById(id));
    }

    /**
     * Crea un nuevo paquete con servicios asociados.
     * @param request La información del paquete a crear y sus servicios.
     * @return ResponseEntity con el paquete creado y el código de estado 200 si la solicitud es exitosa,
     * o un ResponseEntity con el código de estado 400 si ocurre un error durante la creación.
     */
    @PostMapping("/auth/packages")
    private ResponseEntity<Package> createPackage(@RequestBody packageWithServices request) {
        try {
            return ResponseEntity.ok(packageService.createPackage(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }
    }

    /**
     * Actualiza la información de un paquete existente.
     * @param id ID del paquete a actualizar.
     * @param request La información actualizada del paquete.
     * @return ResponseEntity con el paquete actualizado.
     */
    @PutMapping("/auth/packages/{id}")
    private ResponseEntity<Package> updatePackage(@PathVariable int id, @RequestBody packageWithServices request) {
        return ResponseEntity.ok(packageService.updatePackage(id, request));
    }

    /**
     * Elimina un paquete existente.
     * @param id ID del paquete a eliminar.
     * @return ResponseEntity con un mensaje de confirmación.
     */
    @DeleteMapping("/auth/packages/{id}")
    private ResponseEntity<Map<String, Boolean>> deletePackage(@PathVariable int id) {
        return ResponseEntity.ok(packageService.deletePackage(id));
    }
}
