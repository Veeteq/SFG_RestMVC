package com.wojnarowicz.sfg.restmvc.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.wojnarowicz.sfg.restmvc.api.v1.mapper.CustomerMapper;
import com.wojnarowicz.sfg.restmvc.api.v1.model.CustomerDTO;
import com.wojnarowicz.sfg.restmvc.domain.Customer;
import com.wojnarowicz.sfg.restmvc.repositories.CustomerRepository;
import com.wojnarowicz.sfg.restmvc.service.CustomerService;
import com.wojnarowicz.sfg.restmvc.service.impl.CustomerServiceImpl;

class CustomerServiceTest {

    private static final long ID_1 = 1L;
    private static final String JACK = "Jack";
    private static final String BLACK = "Black";
    private static final String API_V1_CUSTOMERS = "/api/v1/customers/";
    
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void testMocks() {
        assertNotNull(customerRepository);
        assertNotNull(customerService);
    }
    
    @Test
    public void testFindAll() {
        Customer customer1 = new Customer();
        customer1.setFirstName(JACK);
        customer1.setLastName(BLACK);
        
        Customer customer2 = new Customer();
        customer2.setFirstName("Vanessa");
        customer2.setLastName("Gray");
        
        List<Customer> customers = Arrays.asList(customer1, customer2);
        
        assertNotNull(customerRepository);
        
        when(customerRepository.findAll()).thenReturn(customers);     
        List<CustomerDTO> customerDTOs = customerService.findAll();
        
        assertEquals(2, customerDTOs.size());
    }
    
    @Test
    public void testFindByLastName() {
        Customer customer1 = new Customer();
        customer1.setFirstName(JACK);
        customer1.setLastName(BLACK);
        
        assertNotNull(customerRepository);
        
        when(customerRepository.findByLastName(anyString())).thenReturn(Arrays.asList(customer1));
        List<CustomerDTO> customerDTOs = customerService.findByLastName(BLACK);
        
        assertEquals(1, customerDTOs.size());
    }
    
    @Test
    public void testFindById() {
        Customer customer1 = new Customer();
        customer1.setId(ID_1);
        customer1.setFirstName(JACK);
        customer1.setLastName(BLACK);
        
        assertNotNull(customerRepository);
        
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer1));
        CustomerDTO customerDTO = customerService.findById(ID_1);
        
        assertEquals(ID_1, customerDTO.getId());
        assertEquals(BLACK, customerDTO.getLastName());
    }
    
    @Test
    public void testSaveDTO() {
        CustomerDTO emptyDTO = new CustomerDTO();
        
        Customer customer1 = new Customer();
        customer1.setId(ID_1);
        customer1.setFirstName(JACK);
        customer1.setLastName(BLACK);
        
        when(customerRepository.save(any(Customer.class))).thenReturn(customer1);
        CustomerDTO savedDTO = customerService.saveDTO(emptyDTO);
        
        assertEquals(ID_1, savedDTO.getId());
        assertEquals(BLACK, savedDTO.getLastName());
        assertEquals(API_V1_CUSTOMERS + ID_1, savedDTO.getCustomerUrl());
    }
    
    @Test
    public void testUpdateDTO() {
        CustomerDTO emptyDTO = new CustomerDTO();
        
        Customer customer1 = new Customer();
        customer1.setId(ID_1);
        customer1.setFirstName(JACK);
        customer1.setLastName(BLACK);
        
        when(customerRepository.save(any(Customer.class))).thenReturn(customer1);
        CustomerDTO savedDTO = customerService.updateDTO(ID_1, emptyDTO);
        
        assertEquals(ID_1, savedDTO.getId());
        assertEquals(BLACK, savedDTO.getLastName());
        assertEquals(API_V1_CUSTOMERS + ID_1, savedDTO.getCustomerUrl());
    }

    @Test
    public void testDeleteById() {
        customerRepository.deleteById(ID_1);
        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}
