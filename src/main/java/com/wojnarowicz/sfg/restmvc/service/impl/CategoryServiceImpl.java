package com.wojnarowicz.sfg.restmvc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojnarowicz.sfg.restmvc.api.v1.mapper.CategoryMapper;
import com.wojnarowicz.sfg.restmvc.api.v1.model.CategoryDTO;
import com.wojnarowicz.sfg.restmvc.repositories.CategoryRespository;
import com.wojnarowicz.sfg.restmvc.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRespository categoryRespository;
	private final CategoryMapper categoryMapper;

	@Autowired
	public CategoryServiceImpl(CategoryRespository categoryRespository, CategoryMapper categoryMapper) {
		this.categoryRespository = categoryRespository;
		this.categoryMapper = categoryMapper;
	}

	@Override
 	public List<CategoryDTO> findAll() {
		return categoryRespository.findAll()
		        .stream()
		        .map(categoryMapper::categoryToCategoryDTO)
		        .collect(Collectors.toList());
	}

	@Override
	public CategoryDTO findByName(String name) {
		return categoryMapper.categoryToCategoryDTO(categoryRespository.findByName(name));
	}
}
