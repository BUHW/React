package com.costs.costs_api.domain.project;

import com.costs.costs_api.domain.services.Services;
import com.costs.costs_api.domain.categories.Categories;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Table(name = "Project")
@Entity
@Setter
@Getter
@NoArgsConstructor
// Pelo que eu vi, vão não está utilizando o construtor com todos os argumentos. Poderia remover
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double budget;

    private Double cost;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories category;

    // usar o getter e setter do lombok podem quebrar a regra de encapsulamento
    // O @Getter permite que o código externo obtenha a referência direta à lista interna.
    // Isso significa que qualquer parte do código que tenha acesso a essa lista pode modificá-la diretamente (adicionando ou removendo elementos)

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Services> services;

    // Sugestão: remover o setter de services e adicionar os métodos addService e removeService

//    public void addService(Service service) {
//        services.add(service);
//        service.setProject(this); // Mantém a relação bidirecional consistente
//    }
//
//    public void removeService(Service service) {
//        services.remove(service);
//        service.setProject(null); // Mantém a relação bidirecional consistente
//    }

    // Sugestão: remover o getter de services e adicionar o método getServices

//    public List<Services> getServices() {
//        return Collections.unmodifiableList(services);
//    }

}
