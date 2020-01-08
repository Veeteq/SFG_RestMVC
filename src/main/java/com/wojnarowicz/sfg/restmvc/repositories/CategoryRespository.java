package com.wojnarowicz.sfg.restmvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wojnarowicz.sfg.restmvc.domain.Category;

public interface CategoryRespository extends JpaRepository<Category, Long> {
}
