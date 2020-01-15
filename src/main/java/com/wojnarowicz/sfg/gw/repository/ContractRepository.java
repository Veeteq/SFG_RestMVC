package com.wojnarowicz.sfg.gw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wojnarowicz.sfg.gw.domain.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long>{

    Contract findBySeriesAndNumber(String series, String number);

}
