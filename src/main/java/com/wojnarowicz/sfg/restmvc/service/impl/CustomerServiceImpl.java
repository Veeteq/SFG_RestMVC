package com.wojnarowicz.sfg.restmvc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojnarowicz.sfg.restmvc.api.v1.mapper.CustomerMapper;
import com.wojnarowicz.sfg.restmvc.api.v1.model.CustomerDTO;
import com.wojnarowicz.sfg.restmvc.domain.Customer;
import com.wojnarowicz.sfg.restmvc.repositories.CustomerRepository;
import com.wojnarowicz.sfg.restmvc.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final String API_V1_CUSTOMERS = "/api/v1/customers/";
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> findAll() {
        return customerRepository.findAll()
        .stream()
        .map(customer -> {
            CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
            customerDTO.setCustomerUrl(API_V1_CUSTOMERS + customer.getId());
            return customerDTO;
        })
        .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findById(Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl(API_V1_CUSTOMERS + customer.getId());
                    return customerDTO;
                })
                .orElseThrow(RuntimeException::new);
    }
    
    @Override
    public List<CustomerDTO> findByLastName(String lastName) {
        return customerRepository.findByLastName(lastName)
                .stream()
                .map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public CustomerDTO saveDTO(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        return saveAndConvertCustomer(customer);
    }

    @Override
    public CustomerDTO updateDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);
        return saveAndConvertCustomer(customer);
    }
    
    @Override
    public CustomerDTO patchDTO(long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {
            if(customerDTO.getFirstName() != null){
                customer.setFirstName(customerDTO.getFirstName());
            }

            if(customerDTO.getLastName() != null){
                customer.setLastName(customerDTO.getLastName());
            }
            
            return saveAndConvertCustomer(customer);
        }).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDTO saveAndConvertCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO savedCustomerDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        savedCustomerDTO.setCustomerUrl(API_V1_CUSTOMERS + savedCustomer.getId());
        return savedCustomerDTO;
    }
}
