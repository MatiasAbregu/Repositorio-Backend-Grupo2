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
 * @author Matias
 */
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ControllerSales {
    
    @Autowired
    private SalesService salesService;
    
    @GetMapping("/auth/sales")
    private ResponseEntity<List<Sales>> getAllSales() {
        return ResponseEntity.ok(salesService.getAllSales());
    }

    @GetMapping("/auth/sales/{id}")
    private ResponseEntity<Sales> getSaleById(@PathVariable int id) {
        return ResponseEntity.ok(salesService.getSaleById(id));
    }

    @PostMapping("/auth/sales")
    private ResponseEntity<Sales> createSale(@RequestBody Sales request) {
        return ResponseEntity.ok(salesService.createSale(request));
    }

    @PutMapping("/auth/sales/{id}")
    private ResponseEntity<Sales> updateSale(@PathVariable int id, @RequestBody Sales request) {
        return ResponseEntity.ok(salesService.updateSale(id, request));
    }
    
    @DeleteMapping("/auth/sales/{id}")
    private ResponseEntity<Map<String, Boolean>> deleteSale(@PathVariable int id){
        return ResponseEntity.ok(salesService.deleteSale(id));
    }
}
