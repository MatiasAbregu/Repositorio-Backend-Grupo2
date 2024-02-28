package com.example.travsky.controlador;

import com.example.travsky.modelo.Servicio;
import com.example.travsky.repositorios.ServicioRepositorio;
import com.example.travsky.views.ServiceView;
import com.fasterxml.jackson.annotation.JsonView;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Matias
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ControllerServicios {

    @Autowired
    private ServicioRepositorio servicioRepositorio;

    @GetMapping("/servicios")
    @JsonView(ServiceView.ServicioSimple.class)
    private ResponseEntity<List<Servicio>> getAllTypeServices(@RequestParam(required = false) String tipo) {
        if (tipo != null) {
            return ResponseEntity.ok(servicioRepositorio.findByTipo(Character.toUpperCase(tipo.charAt(0)) + tipo.substring(1)));
        } else {
            return ResponseEntity.ok(servicioRepositorio.findAll());
        }
    }

    @GetMapping("/servicios/{id}")
    @JsonView(ServiceView.ServicioDetallado.class)
    private ResponseEntity<Servicio> getServiceById(@PathVariable int id) {
        return ResponseEntity.ok(servicioRepositorio.findById(id).orElseThrow());
    }

    @PostMapping("/servicios")
    @JsonView(ServiceView.ServicioDetallado.class)
    private Servicio createOrUpdateService(@RequestBody Servicio request) {
        return servicioRepositorio.save(request);
    }

    @PutMapping("/servicios/{id}")
    @JsonView(ServiceView.ServicioDetallado.class)
    private ResponseEntity<Servicio> updateService(@PathVariable int id, @RequestBody Servicio request) {
        Servicio s = servicioRepositorio.findById(id).orElseThrow();
        s.setS_nombre(request.getS_nombre());
        s.setDescripcion(request.getDescripcion());
        s.setDestino(request.getDestino());
        s.setFecha(request.getFecha());
        s.setTipo(request.getTipo());
        s.setCosto(request.getCosto());

        Servicio sA = servicioRepositorio.save(s);
        return ResponseEntity.ok(sA);
    }

    @DeleteMapping("/servicios/{id}")
    private ResponseEntity<Map<String, Boolean>> deleteService(@PathVariable int id) {
        Servicio s = servicioRepositorio.findById(id).orElseThrow();
        servicioRepositorio.delete(s);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }
}
