package com.wojnarowicz.sfg.restmvc.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.wojnarowicz.sfg.restmvc.api.v1.model.CustomerDTO;
import com.wojnarowicz.sfg.restmvc.domain.Customer;

@Mapper(componentModel="spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    
    CustomerDTO customerToCustomerDTO(Customer customer);
    
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
