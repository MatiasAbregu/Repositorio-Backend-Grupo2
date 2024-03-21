package com.example.travsky.controllers;

import com.example.travsky.models.*;
import com.example.travsky.services.SalesService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para la gestión de ventas en la aplicación.
 */
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ControllerSales {
    
    @Autowired
    private SalesService salesService;
    
    /**
     * Obtiene todas las ventas.
     * @return ResponseEntity con la lista de ventas.
     */
    @GetMapping("/auth/sales")
    private ResponseEntity<List<Sales>> getAllSales() {
        return ResponseEntity.ok(salesService.getAllSales());
    }

    /**
     * Obtiene una venta por su ID.
     * @param id ID de la venta a obtener.
     * @return ResponseEntity con la venta.
     */
    @GetMapping("/auth/sales/{id}")
    private ResponseEntity<Sales> getSaleById(@PathVariable int id) {
        return ResponseEntity.ok(salesService.getSaleById(id));
    }

    /**
     * Crea una nueva venta.
     * @param request La información de la venta a crear.
     * @return ResponseEntity con la venta creada.
     */
    @PostMapping("/auth/sales")
    private ResponseEntity<Sales> createSale(@RequestBody Sales request) {
        return ResponseEntity.ok(salesService.createSale(request));
    }

     /**
     * Actualiza la información de una venta existente.
     * @param id ID de la venta a actualizar.
     * @param request La información actualizada de la venta.
     * @return ResponseEntity con la venta actualizada.
     */
    @PutMapping("/auth/sales/{id}")
    private ResponseEntity<Sales> updateSale(@PathVariable int id, @RequestBody Sales request) {
        return ResponseEntity.ok(salesService.updateSale(id, request));
    }
    
    /**
     * Elimina una venta existente.
     * @param id ID de la venta a eliminar.
     * @return ResponseEntity con un mensaje de confirmación.
     */
    @DeleteMapping("/auth/sales/{id}")
    private ResponseEntity<Map<String, Boolean>> deleteSale(@PathVariable int id){
        return ResponseEntity.ok(salesService.deleteSale(id));
    }
}
