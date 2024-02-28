package com.example.travsky.controlador;

import com.example.travsky.dto.ServiciosDelPaquete;
import com.example.travsky.modelo.Paquete;
import com.example.travsky.modelo.Servicio;
import com.example.travsky.modelo.ServiciosPaquetes;
import com.example.travsky.repositorios.PaqueteRepositorio;
import com.example.travsky.repositorios.ServicioPaquetesRepositorio;
import com.example.travsky.views.ServiceView;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.ArrayList;
import java.util.HashMap;
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
@CrossOrigin(origins = "http://localhost:3000")
public class ControllerPaquetes {
    
    @Autowired
    private PaqueteRepositorio paqueteRepositorio;
    
    @Autowired
    private ServicioPaquetesRepositorio spRepositorio;
    
    @GetMapping("/paquetes")
    private ResponseEntity<List<ServiciosDelPaquete>> getAllPackages(){
        List<Paquete> pList = paqueteRepositorio.findAll();
        
        List<ServiciosDelPaquete> sdpLista = new ArrayList<>();
        for(Paquete p : pList){
            ServiciosDelPaquete sdp = new ServiciosDelPaquete();
            sdp.setNombre(p.getP_nombre());
            
            List<Servicio> listaServicios = new ArrayList<>();
            for(ServiciosPaquetes sp : p.getServPaq()){
                Servicio s = new Servicio(sp.getServicio().getS_codigo(), sp.getServicio().getS_nombre(), 
                        sp.getServicio().getDescripcion(), sp.getServicio().getImagen(), sp.getServicio().getDestino(), sp.getServicio().getFecha(), 
                        sp.getServicio().getTipo(), sp.getServicio().getCosto());
                listaServicios.add(s);
            }
            sdp.setServicios(listaServicios);
            sdpLista.add(sdp);
        }
        
        return ResponseEntity.ok(sdpLista);
    }
    
    @GetMapping("/paquetes/{id}")
    private ResponseEntity<ServiciosDelPaquete> getPackageById(@PathVariable int id){
        Paquete p = paqueteRepositorio.findById(id).orElseThrow();
        
        ServiciosDelPaquete sdp = new ServiciosDelPaquete();
        sdp.setNombre(p.getP_nombre());
        
        List<Servicio> listaServicios = new ArrayList<>();
        for(ServiciosPaquetes sp : p.getServPaq()){
            Servicio s = new Servicio(sp.getServicio().getS_codigo(), sp.getServicio().getS_nombre(), 
                        sp.getServicio().getDescripcion(), sp.getServicio().getImagen(), sp.getServicio().getDestino(), sp.getServicio().getFecha(), 
                        sp.getServicio().getTipo(), sp.getServicio().getCosto());
            
            listaServicios.add(s);
        }
        
        sdp.setServicios(listaServicios);
        
        return ResponseEntity.ok(sdp);
    }

    @PostMapping("/paquetes")
    private Paquete createPackage(@RequestBody ServiciosDelPaquete request){
        Paquete p = new Paquete();
        p.setP_nombre(request.getNombre());
        paqueteRepositorio.save(p);
        
        for(Servicio s : request.getServicios()){
            ServiciosPaquetes sp = new ServiciosPaquetes();
            sp.setPaquete(p);
            sp.setServicio(s);
            
            spRepositorio.save(sp);
        }
        
        return p;
    }
    
    @PutMapping("/paquetes/{id}")
    private ResponseEntity<Paquete> updatePackage(@PathVariable int id, @RequestBody ServiciosDelPaquete request){ //ACTUALIZAR NOMBRE Y CANTIDAD DE SERVICIOS INTERIOR (NO EL SERVICIO)
        Paquete p = paqueteRepositorio.findById(id).orElseThrow();
        p.setP_nombre(request.getNombre());
        Paquete pA = paqueteRepositorio.save(p);
        
   
        
        return ResponseEntity.ok(pA);
    }
    
    @DeleteMapping("/paquetes/{id}")
    private ResponseEntity<Map<String, Boolean>> deletePackage(@PathVariable int id){ //AGREGAR CASCADE, AL ELIMINAR PAQUETE SE ELIMINA SUS RELACIONES EN SERVICIOXPAQUETES
        Paquete p = paqueteRepositorio.findById(id).orElseThrow();
        paqueteRepositorio.delete(p);
        
        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        
        return ResponseEntity.ok(response);
    } 
}