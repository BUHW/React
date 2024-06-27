package com.costs.costs_api.controller;

import com.costs.costs_api.domain.services.Services;
import com.costs.costs_api.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/services")
public class ServicesController {

    private final ServicesService servicesService;

    @Autowired
    public ServicesController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @GetMapping
    public List<Services> getAll() {
        return servicesService.getAll();
    }

    @GetMapping("/{id}")
    public Services getById(@PathVariable UUID id) {
        return servicesService.getById(id);
    }

    @PostMapping
    public Services create(@RequestBody Services services) {
        return servicesService.create(services);
    }

}
