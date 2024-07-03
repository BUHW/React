package com.costs.costs_api.domain.project.exception;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException() {
        super("Category not found");
    }

}
