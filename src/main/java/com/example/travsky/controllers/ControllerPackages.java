package com.example.travsky.controllers;

import com.example.travsky.dto.ServiciosDelPaquete;
import com.example.travsky.models.Package;
import com.example.travsky.models.Service;
import com.example.travsky.models.ServicePackage;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.travsky.repositories.PackageRepository;
import com.example.travsky.repositories.ServicePackageRepository;

/**
 * @author Matias
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ControllerPackages {
    
    @Autowired
    private PackageRepository packageRepository;
    
    @Autowired
    private ServicePackageRepository spRepositorio;
    
    @GetMapping("/paquetes")
    private ResponseEntity<List<ServiciosDelPaquete>> getAllPackages(){
        List<Package> pList = packageRepository.findAll();
        
        List<ServiciosDelPaquete> sdpLista = new ArrayList<>();
        for(Package p : pList){
            ServiciosDelPaquete sdp = new ServiciosDelPaquete();
            sdp.setName(p.getName());
            
            List<Service> listaServicios = new ArrayList<>();
            for(ServicePackage sp : p.getListServices()){
                Service s = new Service(sp.getService().getCode(), sp.getService().getName(), 
                        sp.getService().getDesc(), sp.getService().getImg(), sp.getService().getDestination(), sp.getService().getDate(), 
                        sp.getService().getType(), sp.getService().getPrice(), null);
                listaServicios.add(s);
            }
            sdp.setService(listaServicios);
            sdpLista.add(sdp);
        }
        
        return ResponseEntity.ok(sdpLista);
    }
    
    @GetMapping("/paquetes/{id}")
    private ResponseEntity<ServiciosDelPaquete> getPackageById(@PathVariable int id){
        Package p = packageRepository.findById(id).orElseThrow();
        
        ServiciosDelPaquete sdp = new ServiciosDelPaquete();
        sdp.setName(p.getName());
        
        List<Service> listaServicios = new ArrayList<>();
        for(ServicePackage sp : p.getListServices()){
                Service s = new Service(sp.getService().getCode(), sp.getService().getName(), 
                        sp.getService().getDesc(), sp.getService().getImg(), sp.getService().getDestination(), sp.getService().getDate(), 
                        sp.getService().getType(), sp.getService().getPrice(), null);
                listaServicios.add(s);
            }
            sdp.setService(listaServicios);
       
        return ResponseEntity.ok(sdp);
    }

    @PostMapping("/paquetes")
    private Package createPackage(@RequestBody ServiciosDelPaquete request){
        Package p = new Package();
        p.setName(request.getName());
        packageRepository.save(p);
        
        for(Service s : request.getService()){
            ServicePackage sp = new ServicePackage();
            sp.setPackageName(p);
            sp.setService(s);
            
            spRepositorio.save(sp);
        }
        
        return p;
    }
    
    @PutMapping("/paquetes/{id}")
    private ResponseEntity<Package> updatePackage(@PathVariable int id, @RequestBody ServiciosDelPaquete request){ //ACTUALIZAR NOMBRE Y CANTIDAD DE SERVICIOS INTERIOR (NO EL SERVICIO)
        Package p = packageRepository.findById(id).orElseThrow();
        p.setName(request.getName());
        Package pA = packageRepository.save(p);
        
        return ResponseEntity.ok(pA);
    }
    
    @DeleteMapping("/paquetes/{id}")
    private ResponseEntity<Map<String, Boolean>> deletePackage(@PathVariable int id){ //AGREGAR CASCADE, AL ELIMINAR PAQUETE SE ELIMINA SUS RELACIONES EN SERVICIOXPAQUETES
        Package p = packageRepository.findById(id).orElseThrow();
        packageRepository.delete(p);
        
        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        
        return ResponseEntity.ok(response);
    } 
}