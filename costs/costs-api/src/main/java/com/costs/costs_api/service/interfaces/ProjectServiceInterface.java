package com.costs.costs_api.service.interfaces;

import com.costs.costs_api.domain.project.Project;

import java.util.List;
import java.util.UUID;

public interface ProjectServiceInterface {

    List<Project> getAll();
    Project getById(UUID id);
    Project create(Project project);
    Project update(UUID id, Project project);
    void delete(UUID id);

}
