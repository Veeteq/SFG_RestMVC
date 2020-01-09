package com.wojnarowicz.sfg.restmvc.service;

import java.util.List;

import com.wojnarowicz.sfg.restmvc.api.v1.model.CustomerDTO;

public interface CustomerService {

	List<CustomerDTO> findAll();
	
	List<CustomerDTO> findByLastName(String lastName);

    CustomerDTO findById(Long id);

    CustomerDTO saveDTO(CustomerDTO customerDTO);
}
