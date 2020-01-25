package com.wojnarowicz.sfg.restmvc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wojnarowicz.sfg.restmvc.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {

	Optional<Category> findByName(String name);
}
