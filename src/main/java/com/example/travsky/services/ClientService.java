package com.example.travsky.services;

import com.example.travsky.models.Client;
import com.example.travsky.models.Employee;
import com.example.travsky.models.Person;
import com.example.travsky.models.Sales;
import com.example.travsky.repositories.ClientRepository;
import com.example.travsky.repositories.EmployeeRepository;
import com.example.travsky.repositories.PackageRepository;
import com.example.travsky.repositories.PersonRepository;
import com.example.travsky.repositories.SalesRepository;
import com.example.travsky.repositories.ServiceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author matia
 */

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private ServiceRepository serviceRepository;
    
    @Autowired
    private PackageRepository packageRepository;
    
    @Autowired
    private SalesRepository salesRepository;
    
    public List<Client> getAllClients(){
       return clientRepository.findAll();
    }
    
    public Client getClientById(int id){
        Client c = clientRepository.findById(id).orElseThrow();
        return c;
    }
    
    @Transactional
    public Sales createSale(Sales request){
        Person p = personRepository.findById(request.getClient().getPerson().getDni()).orElseThrow();
        Client c = clientRepository.findByPerson(p).orElse(null);
        Employee e = employeeRepository.findById(request.getEmployee().getCode()).orElseThrow();
        Sales s = new Sales();
        
        if(c == null){
            c = new Client();
            c.setPerson(p);
            Client cCreated = clientRepository.save(c);
            s.setClient(cCreated);
        } else {
            s.setClient(c);
        }

        s.setDate(request.getDate());
        s.setPayment(request.getPayment());
        s.setEmployee(e);
        
        if (request.getService() != null) {
            com.example.travsky.models.Service service = serviceRepository.findById(request.getService().getCode()).orElseThrow();
            s.setService(service);
            s.setPackageName(null);
        } else {
            com.example.travsky.models.Package pack = packageRepository.findById(request.getPackageName().getCode()).orElseThrow();
            s.setPackageName(pack);
            s.setService(null);
        }
        
        Sales sCreated = salesRepository.save(s);
        return sCreated;
    }
}