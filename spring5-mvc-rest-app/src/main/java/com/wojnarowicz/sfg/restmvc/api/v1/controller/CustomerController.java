package com.wojnarowicz.sfg.restmvc.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wojnarowicz.sfg.rest.CustomerDTO;
import com.wojnarowicz.sfg.rest.CustomerListDTO;
import com.wojnarowicz.sfg.restmvc.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "API Customer Controller")
@RestController
@RequestMapping(path = CustomerController.BASE_URL)
public class CustomerController {

    public final static String BASE_URL = "/api/v1/customers"; 
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    @ApiOperation(value = "Returns Customers", notes = "Return all customers", produces = "application/json")
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public CustomerListDTO listCustomers() {
        CustomerListDTO customerListDTO = new CustomerListDTO();
        customerListDTO.getCustomers().addAll(customerService.findAll());
        return customerListDTO;
    }
    
    @ApiOperation(value = "Returns Customer By Id", notes = "Return customers by Id")
    @GetMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public CustomerDTO getCustomerById(@PathVariable(name = "id") String id) {
        Long longId = Long.valueOf(id);
        return customerService.findById(longId);
    }
    
    @PostMapping(consumes="application/json", produces="application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.saveDTO(customerDTO);
    }
    
    @PutMapping(path = "/{id}", consumes="application/json", produces="application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public CustomerDTO updateCustomer(@PathVariable(name = "id") String id, @RequestBody CustomerDTO customerDTO) {
        Long longId = Long.valueOf(id);
        customerDTO.setId(longId);
        return customerService.updateDTO(longId, customerDTO);
    }

    @PatchMapping(path = "/{id}", consumes="application/json", produces="application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public CustomerDTO patchCustomer(@PathVariable(name = "id") String id, @RequestBody CustomerDTO customerDTO) {
        Long longId = Long.valueOf(id);
        customerDTO.setId(longId);
        return customerService.patchDTO(longId, customerDTO);
    }
    
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteById(@PathVariable(name = "id") String id) {
        Long longId = Long.valueOf(id);
        customerService.deleteById(longId);
    }
}
