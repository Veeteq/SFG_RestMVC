package com.wojnarowicz.sfg.restmvc.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wojnarowicz.sfg.restmvc.api.v1.model.CustomerDTO;
import com.wojnarowicz.sfg.restmvc.domain.Customer;

class CustomerMapperTest {

    private static final long ID_1 = 1L;
    private static final long ID_2 = 2L;
    private static final String FIRST_NAME = "Jack";
    private static final String LAST_NAME = "Black";
    CustomerMapper mapper = CustomerMapper.INSTANCE;
    
    Customer customer;
    CustomerDTO customerDTO;
    
    @BeforeEach
    void setUp() throws Exception {
        customer = new Customer();
        customer.setId(ID_1);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        customerDTO = new CustomerDTO();
        customerDTO.setId(ID_2);
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);
    }

    @Test
    void testCategoryToCategoryDTO() {
        CustomerDTO customerDTO = mapper.customerToCustomerDTO(customer);
        assertNotNull(customerDTO);
        assertEquals(ID_1, customerDTO.getId());
        assertEquals(FIRST_NAME, customerDTO.getFirstName());
        assertEquals(LAST_NAME, customerDTO.getLastName());
    }
    
    @Test
    void testCustomerDTOToCustomer() {
        Customer newCustomer = mapper.customerDTOToCustomer(customerDTO);
        assertNotNull(newCustomer);
        assertEquals(ID_2, newCustomer.getId());
        assertEquals(FIRST_NAME, newCustomer.getFirstName());
        assertEquals(LAST_NAME, newCustomer.getLastName());
    }
}
