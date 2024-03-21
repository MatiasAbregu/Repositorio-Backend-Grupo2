package com.example.travsky.services;

import com.example.travsky.repositories.ServiceRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para la gestión de servicios en la aplicación.
 */
@Service
public class ServiceService {
    
    @Autowired
    private ServiceRepository serviceRepository;
    
    /**
     * Obtiene una lista de servicios filtrados por tipo.
     * @param type Tipo de servicio a filtrar (opcional).
     * @return Lista de servicios filtrados.
     */
    public List<com.example.travsky.models.Service> getServicesByType(String type){
        List<com.example.travsky.models.Service> list;
        
        if(type != null){
            list = serviceRepository.findByType(Character.toUpperCase(type.charAt(0)) + type.substring(1));
        } else {
            list = serviceRepository.findAll();
        }
        
        return list;
    }
    
    /**
     * Obtiene una lista de servicios detallados.
     * @return Lista de servicios detallados.
     */
    public List<com.example.travsky.models.Service> getDetailedServices(){
        List<com.example.travsky.models.Service> list = serviceRepository.findAll();
        return list;
    }
    
    /**
     * Encuentra un servicio por su ID.
     * @param id ID del servicio a encontrar.
     * @return El servicio encontrado.
     */
    public com.example.travsky.models.Service findServiceById(int id){
        com.example.travsky.models.Service service = serviceRepository.findById(id).orElseThrow();
        return service;
    }
    
    /**
     * Crea un nuevo servicio.
     * @param request Información del servicio a crear.
     * @return El servicio creado.
     */
    @Transactional
    public com.example.travsky.models.Service createService(com.example.travsky.models.Service request){
        com.example.travsky.models.Service createdService = serviceRepository.save(request);
        return createdService;
    }
    
    /**
     * Actualiza un servicio existente.
     * @param id ID del servicio a actualizar.
     * @param request Información actualizada del servicio.
     * @return El servicio actualizado.
     */
    @Transactional
    public com.example.travsky.models.Service updateService(int id, com.example.travsky.models.Service request){
        com.example.travsky.models.Service s = serviceRepository.findById(id).orElseThrow();
        s.setName(request.getName());
        s.setDesc(request.getDesc());
        s.setImg(request.getImg());
        s.setDestination(request.getDestination());
        s.setDate(request.getDate());
        s.setType(request.getType());
        s.setPrice(request.getPrice());
        
        com.example.travsky.models.Service sUpdated = serviceRepository.save(s);
        return sUpdated;
    }
    
    /**
     * Elimina un servicio existente.
     * @param id ID del servicio a eliminar.
     * @return Un mensaje de confirmación y un indicador de éxito.
     */
    @Transactional
    public Map<String, Boolean> deleteService(int id){
        com.example.travsky.models.Service s = serviceRepository.findById(id).orElseThrow();
        serviceRepository.delete(s);
        
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        
        return response;
    }
}