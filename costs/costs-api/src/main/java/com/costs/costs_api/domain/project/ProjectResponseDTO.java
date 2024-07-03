package com.costs.costs_api.domain.project;

import com.costs.costs_api.domain.categories.Categories;
import com.costs.costs_api.domain.services.Services;

import java.util.List;
import java.util.UUID;

public record ProjectResponseDTO(UUID id, String name, Double budget, Double cost, Categories category, List<Services> services) {
}
