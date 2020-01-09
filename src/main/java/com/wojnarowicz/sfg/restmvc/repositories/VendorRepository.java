package com.wojnarowicz.sfg.restmvc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wojnarowicz.sfg.restmvc.domain.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

	Optional<Vendor> findByName(String name);
}
