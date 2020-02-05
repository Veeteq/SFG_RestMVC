package com.wojnarowicz.sfg.restmvc.api.v1.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wojnarowicz.sfg.restmvc.api.v1.model.VendorDTO;
import com.wojnarowicz.sfg.restmvc.api.v1.model.VendorListDTO;
import com.wojnarowicz.sfg.restmvc.exception.ResourceNotFoundException;
import com.wojnarowicz.sfg.restmvc.exception.RestResponseEntityExceptionHandler;
import com.wojnarowicz.sfg.restmvc.service.VendorService;
class VendorControllerTest {

    private static final Long ID_1 = Long.valueOf(1);
    private static final String VENDOR_NAME = "Exotic Fruits Company";
    @Mock
    VendorService vendorService;
    
    @InjectMocks
    VendorController vendorController;
    
    MockMvc mockMvc;
    
    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
        		.setControllerAdvice(new RestResponseEntityExceptionHandler())
        		.build();
    }

    @Test
    void testMocks() {
        assertNotNull(vendorService);
        assertNotNull(vendorController);
        
    }

    @Test
    void testFindAll() throws Exception {
        VendorDTO vendor1 = new VendorDTO();
        vendor1.setName(VENDOR_NAME);
        
        VendorDTO vendor2 = new VendorDTO();
        vendor2.setName("Vegas Cucumbers");
        
        VendorListDTO vendors = new VendorListDTO(Arrays.asList(vendor1, vendor2));
        
        when(vendorService.findAll()).thenReturn(vendors);
        
        mockMvc.perform(get(VendorController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    void testFindById() throws Exception {
        VendorDTO vendor1 = new VendorDTO();
        vendor1.setId(ID_1);
        vendor1.setName(VENDOR_NAME);
        
        when(vendorService.findById(anyLong())).thenReturn(vendor1);
        
        mockMvc.perform(get(VendorController.BASE_URL +  "/" + ID_1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(ID_1.intValue())))
        .andExpect(jsonPath("$.name", equalTo(VENDOR_NAME)));
    }
    
    @Test
    void testSaveDTO() throws Exception {
        VendorDTO vendor1 = new VendorDTO();
        vendor1.setId(ID_1);
        vendor1.setName(VENDOR_NAME);
        vendor1.setVendorUrl(VendorController.BASE_URL +  "/" + ID_1);
        
        when(vendorService.saveDTO(any(VendorDTO.class))).thenReturn(vendor1);
        mockMvc.perform(post(VendorController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendor1)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name", equalTo(VENDOR_NAME)))
        .andExpect(jsonPath("$.vendorUrl", equalTo(VendorController.BASE_URL + "/" + ID_1)));
    }

    @Test
    void testUpdateDTO() throws Exception {
        VendorDTO vendor1 = new VendorDTO();
        vendor1.setId(ID_1);
        vendor1.setName(VENDOR_NAME);
        vendor1.setVendorUrl(VendorController.BASE_URL + "/" + ID_1);
        
        when(vendorService.updateDTO(any(Long.class), any(VendorDTO.class))).thenReturn(vendor1);
        mockMvc.perform(put(VendorController.BASE_URL +  "/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendor1)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(ID_1.intValue())))
        .andExpect(jsonPath("$.name", equalTo(VENDOR_NAME)))
        .andExpect(jsonPath("$.vendorUrl", equalTo(VendorController.BASE_URL + "/" + ID_1)));
    }

    @Test
    void testPatchDTO() throws Exception {
        //given
        VendorDTO vendor = new VendorDTO();
        vendor.setName("Fred");

        VendorDTO returnDTO = new VendorDTO();
        returnDTO.setId(ID_1);
        returnDTO.setName(vendor.getName());
        returnDTO.setVendorUrl(VendorController.BASE_URL + "/" + ID_1);
        
        when(vendorService.patchDTO(any(Long.class), any(VendorDTO.class))).thenReturn(returnDTO);
        
        mockMvc.perform(patch(VendorController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(returnDTO)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(ID_1.intValue())))
        .andExpect(jsonPath("$.name", equalTo("Fred")))
        .andExpect(jsonPath("$.vendorUrl", equalTo(VendorController.BASE_URL + "/" + ID_1)));
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete(VendorController.BASE_URL + "/" + ID_1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
        
        verify(vendorService, times(1)).deleteById(anyLong());
    }

    @Test
    void testFindByIdNotFound() throws Exception {
        when(vendorService.findById(anyLong())).thenThrow(ResourceNotFoundException.class);
        
        mockMvc.perform(get(VendorController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }
    
    private String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
