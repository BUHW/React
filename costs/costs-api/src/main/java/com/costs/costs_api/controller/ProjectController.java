package com.costs.costs_api.controller;

import com.costs.costs_api.domain.categories.Categories;
import com.costs.costs_api.domain.project.Project;
import com.costs.costs_api.domain.project.ProjectRequestDTO;
import com.costs.costs_api.domain.services.Services;
import com.costs.costs_api.repositories.CategoriesRepository;
import com.costs.costs_api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/projects")
//@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {

    // Como todas essas dependências têm interfaces, você poderia ter passado as interfaces no construtor,
    //  e não as implementações. Isso facilita a troca de implementações e testes de unidade (Inversão de Controle)
    private final ProjectService projectService;
    // O repository deveria ser acessado apenas pelo service
    private final CategoriesRepository categoriesRepository;

    @Autowired
    public ProjectController(ProjectService projectService, CategoriesRepository categoriesRepository) {
        this.projectService = projectService;
        this.categoriesRepository = categoriesRepository;
    }

    @GetMapping
    public List<Project> getAll() {
        return projectService.getAll();
    }

    @GetMapping("/{id}")
    public Project getById(@PathVariable UUID id) {
        return projectService.getById(id);
    }

    @PostMapping
    // O ideal é sempre devolver um DTO ao invés da entidade diretamente
    public Project create(@RequestBody ProjectRequestDTO projectRequestDTO) {
        // regra deveria ficar dentro do ProjectService. O controller serve apenas para receber requisição e enviar resposta
        Project project = new Project();
        project.setName(projectRequestDTO.name());
        project.setBudget(projectRequestDTO.budget());
        project.setCost(projectRequestDTO.cost());

        Categories category = projectRequestDTO.category();
        if (category != null) {
            Categories existingCategory = categoriesRepository.findCategoriesByName(category.getName());
            if (existingCategory != null) {
                project.setCategory(existingCategory);
            } else {
                project.setCategory(categoriesRepository.save(category));
            }
        }

        List<Services> services = projectRequestDTO.services();
        if (services != null) {
            services.forEach(service -> service.setProject(project));
        }
        project.setServices(services);

        return projectService.create(project);
    }

    @PatchMapping("/{id}")
    // O ideal é sempre devolver um DTO ao invés da entidade diretamente
    public Project update(@PathVariable UUID id, @RequestBody ProjectRequestDTO projectRequestDTO) {
        // regra deveria ficar dentro do ProjectService. O controller serve apenas para receber requisição e enviar resposta
        Project existingProject = projectService.getById(id);

        existingProject.setName(projectRequestDTO.name());
        existingProject.setBudget(projectRequestDTO.budget());
        existingProject.setCost(projectRequestDTO.cost());

        Categories category = projectRequestDTO.category();
        if (category != null) {
            Categories existingCategory = categoriesRepository.findCategoriesByName(category.getName());
            if (existingCategory != null) {
                existingProject.setCategory(existingCategory);
            } else {
                existingProject.setCategory(categoriesRepository.save(category));
            }
        } else {
            existingProject.setCategory(null);
        }

        List<Services> updatedServices = projectRequestDTO.services();
        if (updatedServices != null) {
            existingProject.getServices().clear();
            updatedServices.forEach(service -> {
                service.setProject(existingProject);
                existingProject.getServices().add(service);
            });
        } else {
            existingProject.setServices(null);
        }
        return projectService.update(id, existingProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        projectService.delete(id);
        return ResponseEntity.ok("Projeto excluido com sucesso!");
    }

}
