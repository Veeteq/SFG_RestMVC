package com.wojnarowicz.sfg.gw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wojnarowicz.sfg.gw.domain.KiasExpectedPayment;

public interface KiasRepository extends JpaRepository<KiasExpectedPayment, Long>{

}
