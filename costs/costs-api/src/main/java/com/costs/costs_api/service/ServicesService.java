package com.costs.costs_api.service;

import com.costs.costs_api.domain.services.Services;
import com.costs.costs_api.repositories.ServicesRepository;
import com.costs.costs_api.service.interfaces.ServicesServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServicesService implements ServicesServiceInterface {

    private final ServicesRepository servicesRepository;

    @Autowired
    public ServicesService(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    @Override
    public List<Services> getAll() {
        return servicesRepository.findAll();
    }

    @Override
    public Services getById(UUID id) {
        return servicesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Services not found"));
    }

    @Override
    public Services create(Services services) {
        // Não acho esse tratamento de exceção necessário, pois o Spring já trata exceções de banco de dados
        // Você poderia fazer um tratamento de exceção mais genérico dentro de uma classe ExceptionHandler
        try {
            return servicesRepository.save(services);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao realizar a inserção de um serviço, motivo: " + e.getMessage());
        }
    }

    @Override
    public Services update(UUID id, Services services) {
        Services servicesUpdate = servicesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Services not found"));

        servicesUpdate.setName(services.getName());
        servicesUpdate.setCost(services.getCost());
        servicesUpdate.setDescription(services.getDescription());

        return servicesRepository.save(servicesUpdate);
    }

    @Override
    public void delete(UUID id) {
        servicesRepository.deleteById(id);
    }
}
