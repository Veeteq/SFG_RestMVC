package com.wojnarowicz.sfg.restmvc.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wojnarowicz.sfg.restmvc.api.v1.mapper.VendorMapper;
import com.wojnarowicz.sfg.restmvc.api.v1.model.VendorDTO;
import com.wojnarowicz.sfg.restmvc.bootstrap.DataLoader;
import com.wojnarowicz.sfg.restmvc.domain.Vendor;
import com.wojnarowicz.sfg.restmvc.repositories.CategoryRepository;
import com.wojnarowicz.sfg.restmvc.repositories.CustomerRepository;
import com.wojnarowicz.sfg.restmvc.repositories.VendorRepository;
import com.wojnarowicz.sfg.restmvc.service.VendorService;
import com.wojnarowicz.sfg.restmvc.service.impl.VendorServiceImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class VendorServiceImplIT {

    @Autowired
    CategoryRepository categoryRepository;
    
    @Autowired
    CustomerRepository customerRepository;
    
    @Autowired
    VendorRepository vendorRepository;

    VendorService vendorService;

    @BeforeEach
    void setUp() throws Exception {
        System.out.println("Loading Vendor Data");
        System.out.println(vendorRepository.findAll().size());

        //setup data for testing
        DataLoader loader = new DataLoader(categoryRepository, customerRepository, vendorRepository, null);        
        loader.run(); //load data

        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);        
    }

    @Test
    public void patchVendorUpdateName() throws Exception {
        String updatedName = "UpdatedName";
        long id = getVendorIdValue();

        Vendor originalVendor = vendorRepository.getOne(id);
        assertNotNull(originalVendor);
        //save original first name
        String originalName = originalVendor.getName();

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(updatedName);

        vendorService.patchDTO(id, vendorDTO);

        Vendor updatedVendor = vendorRepository.findById(id).get();

        assertNotNull(updatedVendor);
        assertEquals(updatedName, updatedVendor.getName());
        assertThat(originalName, not(equalTo(updatedVendor.getName())));
    }
    
    private Long getVendorIdValue(){
        List<Vendor> vendors = vendorRepository.findAll();

        System.out.println("Vendors Found: " + vendors.size());

        return vendors.get(0).getId();
    }
}
