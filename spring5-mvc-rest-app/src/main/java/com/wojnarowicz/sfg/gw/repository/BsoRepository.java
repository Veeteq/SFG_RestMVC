package com.wojnarowicz.sfg.gw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wojnarowicz.sfg.gw.domain.BsoDocument;


public interface BsoRepository extends JpaRepository<BsoDocument, Long>{

	Optional<BsoDocument> findById(Long id);
	
	Optional<BsoDocument> findBySeriesAndNumberAndType(String series, String number, String type);
}
