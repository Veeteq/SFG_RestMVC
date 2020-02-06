package com.wojnarowicz.sfg.gw.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wojnarowicz.sfg.gw.domain.Agent;

@Repository
@Transactional
public interface AgentRepository extends JpaRepository<Agent, Long>{

	Optional<Agent> findById(Long id);

	Optional<Agent> findByLnrAndSkk(Long lnr, Long skk);

	//Optional<Agent> findByCode(Long code); 

}
