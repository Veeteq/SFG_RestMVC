package com.wojnarowicz.sfg.restmvc.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.wojnarowicz.sfg.restmvc.api.v1.mapper.CategoryMapper;
import com.wojnarowicz.sfg.restmvc.api.v1.model.CategoryDTO;
import com.wojnarowicz.sfg.restmvc.domain.Category;
import com.wojnarowicz.sfg.restmvc.repositories.CategoryRespository;
import com.wojnarowicz.sfg.restmvc.service.impl.CategoryServiceImpl;

public class CategoryServiceTest {

	CategoryService categoryService;
	
	@Mock
	CategoryRespository categoryRespository;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		categoryService = new CategoryServiceImpl(categoryRespository, CategoryMapper.INSTANCE);
	}

	@Test
	public void testFindAll() {
		Category category1 = new Category();
		category1.setName("Fruits");
		
		Category category2 = new Category();
		category2.setName("Vegetables");
		
		List<Category> categories = Arrays.asList(category1, category2);
		
		
		when(categoryRespository.findAll()).thenReturn(categories);		
		List<CategoryDTO> categoryDTOs = categoryService.findAll();
		
		assertEquals(2, categoryDTOs.size());
	}

	@Test
	public void testFindByName() {
	}

}
