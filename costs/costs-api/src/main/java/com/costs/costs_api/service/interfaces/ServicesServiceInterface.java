package com.costs.costs_api.service.interfaces;

import com.costs.costs_api.domain.services.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServicesServiceInterface {

    List<Services> getAll();
    Services getById(UUID id);
    Services create(Services services);
    Services update(UUID id, Services services);
    void delete(UUID id);

}
