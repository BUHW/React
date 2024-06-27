package com.costs.costs_api.service;

import com.costs.costs_api.domain.project.Project;
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
        try {
            if (project.getCost() == null) {
                project.setCost(0.0);
            }
            return projectRepository.save(project);
        } catch(Exception e) {
            throw new RuntimeException("Erro ao realizar a inserção de um projeto, motivo: " + e.getMessage());
        }
    }

    @Override
    public Project update(UUID id, Project project) {
        Project projectUpdate = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        projectUpdate.setName(project.getName());
        projectUpdate.setBudget(project.getBudget());
        projectUpdate.setCost(project.getCost());
        projectUpdate.setCategories(project.getCategories());
        projectUpdate.setServices(project.getServices());


        return projectRepository.save(projectUpdate);
    }

    @Override
    public void delete(UUID id) {
        projectRepository.deleteById(id);
    }

}
