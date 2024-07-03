package com.costs.costs_api.service;

import com.costs.costs_api.domain.project.Project;
import com.costs.costs_api.domain.services.Services;
import com.costs.costs_api.repositories.ProjectRepository;
import com.costs.costs_api.service.interfaces.ProjectServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService implements ProjectServiceInterface {
    
    private final ProjectRepository projectRepository;
    
    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    
    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project getById(UUID id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    @Override
    public Project create(Project project) {
        project.getServices().forEach(service -> service.setProject(project));
        return projectRepository.save(project);
    }

    @Override
    public Project update(UUID id, Project updatedProject) {
        Project projectToUpdate = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        projectToUpdate.setName(updatedProject.getName());
        projectToUpdate.setBudget(updatedProject.getBudget());
        projectToUpdate.setCost(updatedProject.getCost());
        projectToUpdate.setCategory(updatedProject.getCategory());
        List<Services> updatedServices = updatedProject.getServices();
        if (updatedServices != null) {
            projectToUpdate.getServices().clear();
            updatedServices.forEach(service -> {
                service.setProject(projectToUpdate);
                projectToUpdate.getServices().add(service);
            });
        }

        return projectRepository.save(projectToUpdate);
    }

    @Override
    public void delete(UUID id) {
        projectRepository.deleteById(id);
    }

}
