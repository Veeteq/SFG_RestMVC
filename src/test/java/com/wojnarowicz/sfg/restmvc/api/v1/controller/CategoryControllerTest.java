package com.wojnarowicz.sfg.restmvc.api.v1.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.wojnarowicz.sfg.restmvc.api.v1.model.CategoryDTO;
import com.wojnarowicz.sfg.restmvc.exception.ResourceNotFoundException;
import com.wojnarowicz.sfg.restmvc.exception.RestResponseEntityExceptionHandler;
import com.wojnarowicz.sfg.restmvc.service.CategoryService;
class CategoryControllerTest {

    @Mock
    CategoryService categoryService;
    
    @InjectMocks
    CategoryController categoryController;
    
    MockMvc mockMvc;
    
    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void testMocks() {
        assertNotNull(categoryService);
        assertNotNull(categoryController);
        
    }

    @Test
    void testFindAll() throws Exception {
        CategoryDTO category1 = new CategoryDTO();
        category1.setName("Fruits");
        
        CategoryDTO category2 = new CategoryDTO();
        category2.setName("Vegetables");
        
        List<CategoryDTO> categories = Arrays.asList(category1, category2);
        
        when(categoryService.findAll()).thenReturn(categories);
        
        mockMvc.perform(get("/api/v1/categories")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.categories", hasSize(2)));
    }

    @Test
    void testFindByName() throws Exception {
    	String ID = "cat1";
    	String NAME = "Fruits";
    	
        CategoryDTO category1 = new CategoryDTO();
        category1.setName(NAME);
        category1.setId(ID);
        
        when(categoryService.findByName(anyString())).thenReturn(category1);
        
        mockMvc.perform(get("/api/v1/categories/Fruit")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", equalTo(NAME)))
        .andExpect(jsonPath("$.id", equalTo(ID)));
    }
    
    @Test
    void testFindByNameNotFound() throws Exception {
        when(categoryService.findByName(anyString())).thenThrow(ResourceNotFoundException.class);
        
        mockMvc.perform(get(CategoryController.BASE_URL + "/bla")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }
}
