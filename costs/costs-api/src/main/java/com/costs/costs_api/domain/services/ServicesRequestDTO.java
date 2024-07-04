package com.costs.costs_api.domain.services;

import com.costs.costs_api.domain.project.Project;

public record ServicesRequestDTO(String name, Double cost, String Description, Project project_id) {
}
