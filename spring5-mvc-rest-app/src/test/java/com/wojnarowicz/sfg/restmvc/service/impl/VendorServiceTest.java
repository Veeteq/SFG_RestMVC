package com.wojnarowicz.sfg.restmvc.service.impl;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.wojnarowicz.sfg.restmvc.api.v1.controller.VendorController;
import com.wojnarowicz.sfg.restmvc.api.v1.mapper.VendorMapper;
import com.wojnarowicz.sfg.restmvc.api.v1.model.VendorDTO;
import com.wojnarowicz.sfg.restmvc.api.v1.model.VendorListDTO;
import com.wojnarowicz.sfg.restmvc.domain.Vendor;
import com.wojnarowicz.sfg.restmvc.exception.ResourceNotFoundException;
import com.wojnarowicz.sfg.restmvc.repositories.VendorRepository;
import com.wojnarowicz.sfg.restmvc.service.VendorService;

class VendorServiceTest {

    private static final long ID_1 = 1L;
    private static final String VENDOR_NAME = "Fun Fresh Fruits Ltd";
    
    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    void testMocks() {
        assertNotNull(vendorRepository);
        assertNotNull(vendorService);
    }
    
    @Test
    public void testFindAll() {
        Vendor vendor1 = new Vendor();
        vendor1.setName(VENDOR_NAME);
        
        Vendor vendor2 = new Vendor();
        vendor2.setName("Vanessa");
        
        List<Vendor> vendors = Arrays.asList(vendor1, vendor2);
        
        assertNotNull(vendorRepository);
        
        given(vendorRepository.findAll()).willReturn(vendors);
        
        VendorListDTO vendorDTOs = vendorService.findAll();
        
        assertEquals(2, vendorDTOs.getVendors().size());
        assertThat(vendorDTOs.getVendors().size(), is(equalTo(2)));
    }
    
    @Test
    public void testFindById() {
        Vendor vendor1 = new Vendor();
        vendor1.setId(ID_1);
        vendor1.setName(VENDOR_NAME);
        
        assertNotNull(vendorRepository);
        
        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor1));
        
        VendorDTO vendorDTO = vendorService.findById(ID_1);
        
        assertEquals(ID_1, vendorDTO.getId());
        assertEquals(VENDOR_NAME, vendorDTO.getName());
    }

    @Test
    public void testFindByIdNotFound() {
        //given
        given(vendorRepository.findById(anyLong())).willReturn(Optional.empty());
        
        //when        
        assertThrows(ResourceNotFoundException.class, () -> {
            @SuppressWarnings("unused")
            VendorDTO vendorDTO = vendorService.findById(ID_1);
        });
        
        //then
        then(vendorRepository).should(times(1)).findById(anyLong());
    }
    
    @Test
    public void testSaveDTO() {
        VendorDTO emptyDTO = new VendorDTO();
        
        Vendor vendor1 = new Vendor();
        vendor1.setId(ID_1);
        vendor1.setName(VENDOR_NAME);
        
        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor1);
        VendorDTO savedDTO = vendorService.saveDTO(emptyDTO);
        
        assertEquals(ID_1, savedDTO.getId());
        assertEquals(VENDOR_NAME, savedDTO.getName());
        assertEquals(VendorController.BASE_URL + "/" + ID_1, savedDTO.getVendorUrl());
    }
    
    @Test
    public void testUpdateDTO() {
        VendorDTO emptyDTO = new VendorDTO();
        
        Vendor vendor1 = new Vendor();
        vendor1.setId(ID_1);
        vendor1.setName(VENDOR_NAME);
        
        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor1);
        VendorDTO savedDTO = vendorService.updateDTO(ID_1, emptyDTO);
                
        assertEquals(ID_1, savedDTO.getId());
        assertEquals(VENDOR_NAME, savedDTO.getName());
        assertEquals(VendorController.BASE_URL + "/" + ID_1, savedDTO.getVendorUrl());
        
        assertThat(savedDTO.getVendorUrl(), containsString("1"));
    }

    @Test
    public void testPatchDTO() {
        VendorDTO emptyDTO = new VendorDTO();
        
        Vendor vendor1 = new Vendor();
        vendor1.setId(ID_1);
        vendor1.setName(VENDOR_NAME);
        
        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor1));
        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor1);
        
        VendorDTO savedDTO = vendorService.patchDTO(ID_1, emptyDTO);
                
        then(vendorRepository).should().save(any(Vendor.class));
        then(vendorRepository).should(times(1)).findById(anyLong());
        
        assertEquals(ID_1, savedDTO.getId());
        assertEquals(VENDOR_NAME, savedDTO.getName());
        assertEquals(VendorController.BASE_URL + "/" + ID_1, savedDTO.getVendorUrl());
        
        assertThat(savedDTO.getVendorUrl(), containsString("1"));
    }

    @Test
    public void testDeleteById() {
        vendorRepository.deleteById(ID_1);
        verify(vendorRepository, times(1)).deleteById(anyLong());
        then(vendorRepository).should().deleteById(anyLong());
    }
}
