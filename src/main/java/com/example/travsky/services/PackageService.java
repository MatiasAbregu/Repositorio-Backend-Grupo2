package com.example.travsky.services;

import com.example.travsky.dto.packageWithServices;
import com.example.travsky.models.ServicePackage;
import com.example.travsky.repositories.PackageRepository;
import com.example.travsky.repositories.ServicePackageRepository;
import com.example.travsky.repositories.ServiceRepository;
import java.util.ArrayList;
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
public class PackageService {

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private ServicePackageRepository spRepository;

    @Autowired
    private ServiceRepository serviceReposity;

    public List<packageWithServices> getAllPackages() {
        List<com.example.travsky.models.Package> pList = packageRepository.findAll();

        List<packageWithServices> pwsList = new ArrayList<>();
        for (com.example.travsky.models.Package p : pList) {
            packageWithServices pws = new packageWithServices();

            pws.setPackageInfo(p);
            List<com.example.travsky.models.Service> servicesOfPackage = new ArrayList<>();
            for (ServicePackage sp : p.getListServices()) {
                com.example.travsky.models.Service s = new com.example.travsky.models.Service(sp.getService().getCode(), sp.getService().getName(),
                        sp.getService().getDesc(), sp.getService().getImg(), sp.getService().getDestination(), sp.getService().getDate(),
                        sp.getService().getType(), sp.getService().getPrice(), null, sp.getCode());
                servicesOfPackage.add(s);
            }
            pws.setServices(servicesOfPackage);
            pwsList.add(pws);
        }

        return pwsList;
    }

    public packageWithServices getPackageById(int id) {
        com.example.travsky.models.Package p = packageRepository.findById(id).orElseThrow();

        packageWithServices pws = new packageWithServices();
        pws.setPackageInfo(p);

        List<com.example.travsky.models.Service> servicesList = new ArrayList<>();

        for (ServicePackage sp : p.getListServices()) {
            com.example.travsky.models.Service s = new com.example.travsky.models.Service(sp.getService().getCode(), sp.getService().getName(),
                    sp.getService().getDesc(), sp.getService().getImg(), sp.getService().getDestination(), sp.getService().getDate(),
                    sp.getService().getType(), sp.getService().getPrice(), null, sp.getCode());
            servicesList.add(s);
        }
        pws.setServices(servicesList);

        return pws;
    }

    @Transactional
    public com.example.travsky.models.Package createPackage(packageWithServices request) {
        com.example.travsky.models.Package p = new com.example.travsky.models.Package();
        p.setName(request.getPackageInfo().getName());
        com.example.travsky.models.Package pCreated = packageRepository.save(p);

        if (request.getServices().size() > 2) {
            for (com.example.travsky.models.Service s : request.getServices()) {
                ServicePackage sp = new ServicePackage();
                sp.setPackageName(p);

                com.example.travsky.models.Service sF = serviceReposity.findById(s.getCode()).orElseThrow();
                sp.setService(sF);

                spRepository.save(sp);
            }
            return pCreated;
        } else {
            throw new RuntimeException("Insuficientes servcicios en el paquete. Min 2.");
        }
    }

    @Transactional
    public com.example.travsky.models.Package updatePackage(int id, packageWithServices request) {
        com.example.travsky.models.Package p = packageRepository.findById(id).orElseThrow();
        List<ServicePackage> lsp = spRepository.findByPackageName(p);

        for (com.example.travsky.models.Service s : request.getServices()) {
            if (s.getIdSxP() == 0) {
                ServicePackage sp = new ServicePackage();
                sp.setPackageName(p);
                sp.setService(s);
                spRepository.save(sp);
            }
        }

        for (ServicePackage sp : lsp) {
            boolean exist = false;

            for (com.example.travsky.models.Service s : request.getServices()) {
                if (sp.getCode() == s.getIdSxP()) {
                    exist = true;
                    break;
                }
            }

            if (!exist) {
                spRepository.delete(sp);
            }
        }

        p.setName(request.getPackageInfo().getName());

        com.example.travsky.models.Package pUpdated = packageRepository.save(p);
        return pUpdated;
    }

    @Transactional
    public Map<String, Boolean> deletePackage(int id) {
        com.example.travsky.models.Package p = packageRepository.findById(id).orElseThrow();
        List<ServicePackage> lsp = p.getListServices();

        for (ServicePackage sp : lsp) {
            spRepository.delete(sp);
        }

        packageRepository.delete(p);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);

        return response;
    }

}
