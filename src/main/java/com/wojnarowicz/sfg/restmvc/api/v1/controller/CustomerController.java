package com.wojnarowicz.sfg.restmvc.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wojnarowicz.sfg.restmvc.api.v1.model.CustomerDTO;
import com.wojnarowicz.sfg.restmvc.api.v1.model.CustomerListDTO;
import com.wojnarowicz.sfg.restmvc.service.CustomerService;

@Controller
@RequestMapping(path = "/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    @GetMapping
    public ResponseEntity<CustomerListDTO> listCustomers() {
        CustomerListDTO customerListDTO = new CustomerListDTO(customerService.findAll());
        ResponseEntity<CustomerListDTO> response = new ResponseEntity<CustomerListDTO>(customerListDTO, HttpStatus.OK);
        return response;
    }
    
    @GetMapping(path = "/{id}") 
    public ResponseEntity<CustomerDTO> getCustomerByName(@PathVariable(name = "id") String id) {
        Long longId = Long.valueOf(id);
        CustomerDTO customerDTO = customerService.findById(longId);
        ResponseEntity<CustomerDTO> response = new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
        return response;
    }
    
    @PostMapping(consumes="application/json", produces="application/json")
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO savedCustomerDTO = customerService.saveDTO(customerDTO);
        ResponseEntity<CustomerDTO> response = new ResponseEntity<CustomerDTO>(savedCustomerDTO, HttpStatus.CREATED);
        return response;
    }
    
    @PutMapping(path = "/{id}", consumes="application/json", produces="application/json")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable(name = "id") String id, @RequestBody CustomerDTO customerDTO) {
        Long longId = Long.valueOf(id);
        customerDTO.setId(longId);
        CustomerDTO savedCustomerDTO = customerService.updateDTO(longId, customerDTO);
        ResponseEntity<CustomerDTO> response = new ResponseEntity<CustomerDTO>(savedCustomerDTO, HttpStatus.OK);
        return response;
    }

    @PatchMapping(path = "/{id}", consumes="application/json", produces="application/json")
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable(name = "id") String id, @RequestBody CustomerDTO customerDTO) {
        Long longId = Long.valueOf(id);
        customerDTO.setId(longId);
        CustomerDTO savedCustomerDTO = customerService.patchDTO(longId, customerDTO);
        ResponseEntity<CustomerDTO> response = new ResponseEntity<CustomerDTO>(savedCustomerDTO, HttpStatus.OK);
        return response;
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") String id) {
        Long longId = Long.valueOf(id);
        customerService.deleteById(longId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
