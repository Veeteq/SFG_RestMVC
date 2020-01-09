package com.wojnarowicz.sfg.restmvc.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wojnarowicz.sfg.restmvc.api.v1.model.CategoryDTO;
import com.wojnarowicz.sfg.restmvc.api.v1.model.CategoryListDTO;
import com.wojnarowicz.sfg.restmvc.service.CategoryService;

@RestController
@RequestMapping(path = CategoryController.BASE_URL)
public class CategoryController {

    public static final String  BASE_URL = "/api/v1/categories";
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public CategoryListDTO listCategories() {
        return new CategoryListDTO(categoryService.findAll());
    }
    
    @GetMapping(path = "/{name}") 
    @ResponseStatus(code = HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable(name = "name") String name) {
        return categoryService.findByName(name);
    }
}
