package com.wojnarowicz.sfg.gw.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wojnarowicz.sfg.gw.domain.BsoDocument;


public interface BsoRepository extends JpaRepository<BsoDocument, Long>{

	Optional<BsoDocument> findById(Long id);
	
	List<BsoDocument> findBySeriesAndNumberAndType(String series, String number, String type);
}
