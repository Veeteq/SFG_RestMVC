package com.wojnarowicz.sfg.restmvc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.wojnarowicz.sfg.restmvc.api.v1.mapper.CategoryMapper;
import com.wojnarowicz.sfg.restmvc.api.v1.model.CategoryDTO;
import com.wojnarowicz.sfg.restmvc.domain.Category;
import com.wojnarowicz.sfg.restmvc.repositories.CategoryRepository;
import com.wojnarowicz.sfg.restmvc.service.impl.CategoryServiceImpl;

class CategoryServiceTest {

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRespository;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        categoryService = new CategoryServiceImpl(categoryRespository, CategoryMapper.INSTANCE);
    }

    @Test
    void testMocks() {
        assertNotNull(categoryRespository);
        assertNotNull(categoryService);
    }
    
    @Test
    public void testFindAll() {
        Category category1 = new Category();
        category1.setName("Fruits");
        
        Category category2 = new Category();
        category2.setName("Vegetables");
        
        List<Category> categories = Arrays.asList(category1, category2);
        
        assertNotNull(categoryRespository);
        
        when(categoryRespository.findAll()).thenReturn(categories);     
        List<CategoryDTO> categoryDTOs = categoryService.findAll();
        
        assertEquals(2, categoryDTOs.size());
    }
    
    @Test
    public void testFindByName() {
        Category category1 = new Category();
        category1.setName("Fruits");
        
        assertNotNull(categoryRespository);
        
        when(categoryRespository.findByName(anyString())).thenReturn(category1);
        CategoryDTO categoryDTO = categoryService.findByName("Fruits");
        
        assertEquals("Fruits", categoryDTO.getName());
    }
}
