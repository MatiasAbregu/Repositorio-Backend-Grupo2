package com.example.travsky.services;

import com.example.travsky.models.Client;
import com.example.travsky.models.Employee;
import com.example.travsky.models.Sales;
import com.example.travsky.repositories.ClientRepository;
import com.example.travsky.repositories.EmployeeRepository;
import com.example.travsky.repositories.PackageRepository;
import com.example.travsky.repositories.SalesRepository;
import com.example.travsky.repositories.ServiceRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author matia
 */
@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    public Sales getSaleById(int id) {
        Sales s = salesRepository.findById(id).orElseThrow();
        return s;
    }

    @Transactional
    public Sales createSale(Sales request) {
        Sales s = new Sales();
        s.setDate(request.getDate());
        s.setPayment(request.getPayment());

        Client c = clientRepository.findById(request.getClient().getCode()).orElseThrow();
        s.setClient(c);

        if (request.getService() != null) {
            com.example.travsky.models.Service service = serviceRepository.findById(request.getService().getCode()).orElseThrow();
            s.setService(service);
            s.setPackageName(null);
        } else {
            com.example.travsky.models.Package p = packageRepository.findById(request.getPackageName().getCode()).orElseThrow();
            s.setPackageName(p);
            s.setService(null);
        }

        Employee e = employeeRepository.findById(request.getEmployee().getCode()).orElseThrow();
        s.setEmployee(e);

        Sales sCreated = salesRepository.save(s);

        return sCreated;
    }
    
    @Transactional
    public Sales updateSale(int id, Sales request){
        Sales s = salesRepository.findById(id).orElseThrow();
        s.setDate(request.getDate());
        s.setPayment(request.getPayment());

        Client c = clientRepository.findById(request.getClient().getCode()).orElseThrow();
        s.setClient(c);
        
        if (request.getService() != null) {
            com.example.travsky.models.Service service = serviceRepository.findById(request.getService().getCode()).orElseThrow();
            s.setService(service);
            s.setPackageName(null);
        } else {
            com.example.travsky.models.Package p = packageRepository.findById(request.getPackageName().getCode()).orElseThrow();
            s.setPackageName(p);
            s.setService(null);
        }

        Employee e = employeeRepository.findById(request.getEmployee().getCode()).orElseThrow();
        s.setEmployee(e);

        Sales sUpdated = salesRepository.save(s);

        return sUpdated;
    }
    
    public Map<String, Boolean> deleteSale(int id){
        Sales s = salesRepository.findById(id).orElseThrow();
        salesRepository.delete(s);
        
        Map<String, Boolean> response = new HashMap<>();
        response.put("¡Datos personales del empleado eliminados con éxito!", Boolean.TRUE);

        return response;
    }
}
