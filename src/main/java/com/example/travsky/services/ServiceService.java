package com.example.travsky.services;

import com.example.travsky.repositories.ServiceRepository;
import com.example.travsky.views.ServiceView;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author matia
 */

@Service
public class ServiceService {
    
    @Autowired
    private ServiceRepository serviceRepository;
    
    public List<com.example.travsky.models.Service> getServicesByType(String type){
        List<com.example.travsky.models.Service> list;
        
        if(type != null){
            list = serviceRepository.findByType(Character.toUpperCase(type.charAt(0)) + type.substring(1));
        } else {
            list = serviceRepository.findAll();
        }
        
        return list;
    }
    
    public List<com.example.travsky.models.Service> getDetailedServices(){
        List<com.example.travsky.models.Service> list = serviceRepository.findAll();
        return list;
    }
    
    public com.example.travsky.models.Service findServiceById(int id){
        com.example.travsky.models.Service service = serviceRepository.findById(id).orElseThrow();
        return service;
    }
    
    @Transactional
    public com.example.travsky.models.Service createService(com.example.travsky.models.Service request){
        com.example.travsky.models.Service createdService = serviceRepository.save(request);
        return createdService;
    }
    
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
    
    @Transactional
    public Map<String, Boolean> deleteService(int id){
        com.example.travsky.models.Service s = serviceRepository.findById(id).orElseThrow();
        serviceRepository.delete(s);
        
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        
        return response;
    }
}