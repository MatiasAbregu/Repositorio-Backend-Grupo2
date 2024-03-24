
package com.example.travsky.services;

import com.example.travsky.models.Service;
import com.example.travsky.repositories.ServiceRepository;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestService {

    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private ServiceService serviceService;

    @Test
    public void test_getServicesByType() {
        String type = "Hoteles";

        List<Service> listOfServices = Arrays.asList(new Service(), new Service());
        Mockito.when(serviceService.getServicesByType(type)).thenReturn(listOfServices);

        List<Service> list = serviceService.getServicesByType(type);

        Assertions.assertEquals(list, listOfServices);
        Mockito.verify(serviceRepository, Mockito.times(1)).findByType(type);
    }

    @Test
    public void test_getDetailedServices() {
        List<Service> listOfServices = Arrays.asList(new Service(), new Service());
        Mockito.when(serviceService.getDetailedServices()).thenReturn(listOfServices);

        List<Service> list = serviceService.getDetailedServices();
        
        Assertions.assertEquals(list, listOfServices);
        Mockito.verify(serviceRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void test_getAnExistServiceById(){
        Service serviceMock = new Service();
        serviceMock.setCode(30);
        
        Mockito.when(serviceRepository.findById(serviceMock.getCode())).thenReturn(Optional.of(serviceMock));   
        Service service = serviceService.findServiceById(serviceMock.getCode());
        
        Assertions.assertEquals(service, serviceMock);
        Mockito.verify(serviceRepository, Mockito.times(1)).findById(serviceMock.getCode());
    }
    
    @Test
    public void test_createService(){
        Service serviceMock = new Service();
        serviceMock.setName("Nombre");
        serviceMock.setDesc("Desc");
        serviceMock.setDate(LocalDate.MIN);
        serviceMock.setDestination("Destination");
        serviceMock.setImg("Imagen");
        serviceMock.setPrice(0);
        serviceMock.setType("Tipo");
        
        Mockito.when(serviceRepository.save(serviceMock)).thenReturn(serviceMock);
        Service service = serviceService.createService(serviceMock);
        
        Assertions.assertEquals(service, serviceMock);
        Mockito.verify(serviceRepository, Mockito.times(1)).save(serviceMock);
    }
    
    @Test
    public void test_updateService(){
        Service existService = new Service();
        existService.setCode(1);
        existService.setName("Nombre existente");
        
        Service updatedService = new Service();
        updatedService.setCode(1);
        updatedService.setName("Nombre actualizado");
        
        Mockito.when(serviceRepository.findById(updatedService.getCode())).thenReturn(Optional.of(existService));
        Mockito.when(serviceRepository.save(updatedService)).thenReturn(updatedService);
        
        Service service = serviceService.updateService(updatedService.getCode(), updatedService);
        
        Assertions.assertEquals(updatedService, service);
        Assertions.assertEquals(updatedService.getName(), service.getName());
        Mockito.verify(serviceRepository, Mockito.times(1)).save(updatedService);
    }
    
    @Test
    public void test_deleteService(){
        Service existService = new Service();
        existService.setCode(1);
        
        Mockito.when(serviceRepository.findById(existService.getCode())).thenReturn(Optional.of(existService));
        
        Map<String, Boolean> response = serviceService.deleteService(existService.getCode());
        
        Assertions.assertTrue(response.get("deleted"));
        Mockito.verify(serviceRepository, Mockito.times(1)).delete(existService);
    }
}