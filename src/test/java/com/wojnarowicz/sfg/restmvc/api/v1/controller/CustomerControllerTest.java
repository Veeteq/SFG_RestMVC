package com.wojnarowicz.sfg.restmvc.api.v1.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wojnarowicz.sfg.restmvc.api.v1.model.CustomerDTO;
import com.wojnarowicz.sfg.restmvc.service.CustomerService;
class CustomerControllerTest {

    private static final Long ID_1 = Long.valueOf(1);
    private static final String JACK = "Jack";
    private static final String BLACK = "Black";
    private static final String API_V1_CUSTOMERS = "/api/v1/customers/";    
    @Mock
    CustomerService customerService;
    
    @InjectMocks
    CustomerController customerController;
    
    MockMvc mockMvc;
    
    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void testMocks() {
        assertNotNull(customerService);
        assertNotNull(customerController);
        
    }

    @Test
    void testFindAll() throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName(JACK);
        customer1.setLastName(BLACK);
        
        CustomerDTO customer2 = new CustomerDTO();
        customer2.setLastName("Vegas");
        
        List<CustomerDTO> customers = Arrays.asList(customer1, customer2);
        
        when(customerService.findAll()).thenReturn(customers);
        
        mockMvc.perform(get("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    void testFindById() throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setId(ID_1);
        customer1.setFirstName(JACK);
        customer1.setLastName(BLACK);
        
        when(customerService.findById(anyLong())).thenReturn(customer1);
        
        mockMvc.perform(get("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.lastName", equalTo(BLACK)))
        .andExpect(jsonPath("$.id", equalTo(ID_1.intValue())));
    }
    
    @Test
    void testSaveDTO() throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setId(ID_1);
        customer1.setFirstName(JACK);
        customer1.setLastName(BLACK);
        customer1.setCustomerUrl(API_V1_CUSTOMERS + ID_1);
        
        when(customerService.saveDTO(any(CustomerDTO.class))).thenReturn(customer1);
        mockMvc.perform(post("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer1)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.firstName", equalTo(JACK)))
        .andExpect(jsonPath("$.lastName", equalTo(BLACK)))
        .andExpect(jsonPath("$.customerUrl", equalTo(API_V1_CUSTOMERS + ID_1)));
    }

    private String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}