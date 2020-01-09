package com.wojnarowicz.sfg.restmvc.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wojnarowicz.sfg.restmvc.api.v1.model.CategoryDTO;
import com.wojnarowicz.sfg.restmvc.api.v1.model.CategoryListDTO;
import com.wojnarowicz.sfg.restmvc.service.CategoryService;

@Controller
@RequestMapping(path = CategoryController.BASE_URL)
public class CategoryController {

    public static final String  BASE_URL = "/api/v1/categories";
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    @GetMapping
    public ResponseEntity<CategoryListDTO> listCategories() {
        CategoryListDTO categoryListDTO = new CategoryListDTO(categoryService.findAll());
        ResponseEntity<CategoryListDTO> response = new ResponseEntity<CategoryListDTO>(categoryListDTO, HttpStatus.OK);
        return response;
    }
    
    @GetMapping(path = "/{name}") 
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable(name = "name") String name) {
        CategoryDTO categoryDTO = categoryService.findByName(name);
        ResponseEntity<CategoryDTO> response = new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);
        return response;
    }
}
