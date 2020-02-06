package com.wojnarowicz.sfg.restmvc.service;

import java.util.List;

import com.wojnarowicz.sfg.rest.CustomerDTO;

public interface CustomerService {

	List<CustomerDTO> findAll();
	
	List<CustomerDTO> findByLastName(String lastName);

    CustomerDTO findById(Long id);

    CustomerDTO saveDTO(CustomerDTO customerDTO);

    CustomerDTO updateDTO(Long longId, CustomerDTO customerDTO);

    CustomerDTO patchDTO(long id, CustomerDTO customerDTO);
    
    void deleteById(Long id);
}
