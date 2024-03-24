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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para la gestión de ventas en la aplicación.
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

    /**
     * Obtiene todas las ventas registradas en la base de datos.
     * @return Lista de ventas.
     */
    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    /**
     * Obtiene una venta específica por su ID.
     * @param id ID de la venta a obtener.
     * @return La venta encontrada.
     */
    public Sales getSaleById(int id) {
        Sales s = salesRepository.findById(id).orElseThrow();
        return s;
    }

    /**
     * Crea una nueva venta.
     * Verifica si la venta es de un servicio o un paquete.
     * @param request Información de la venta a crear.
     * @return La venta creada.
     */
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
    
    /**
     * Actualiza una venta existente.
     * @param id ID de la venta a actualizar.
     * @param request Información actualizada de la venta.
     * @return La venta actualizada.
     */
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
    
    /**
     * Elimina una venta existente.
     * @param id ID de la venta a eliminar.
     * @return Un mensaje de confirmación y un indicador de éxito.
     */
    public Map<String, Boolean> deleteSale(int id){
        Sales s = salesRepository.findById(id).orElseThrow();
        salesRepository.delete(s);
        
        Map<String, Boolean> response = new HashMap<>();
        response.put("¡Datos de la venta eliminados con éxito!", Boolean.TRUE);

        return response;
    }
}