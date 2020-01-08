package com.wojnarowicz.sfg.restmvc.service;

import java.util.List;

import com.wojnarowicz.sfg.restmvc.api.v1.model.CategoryDTO;

public interface CategoryService {

	List<CategoryDTO> findAll();
	
	CategoryDTO findByName(String name);
}
