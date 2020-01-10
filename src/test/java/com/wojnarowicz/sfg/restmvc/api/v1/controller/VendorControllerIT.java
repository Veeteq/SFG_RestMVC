package com.wojnarowicz.sfg.restmvc.api.v1.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wojnarowicz.sfg.restmvc.api.v1.model.VendorDTO;
import com.wojnarowicz.sfg.restmvc.api.v1.model.VendorListDTO;
import com.wojnarowicz.sfg.restmvc.exception.ResourceNotFoundException;
import com.wojnarowicz.sfg.restmvc.service.VendorService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {VendorController.class})
class VendorControllerIT {

    private static final Long ID_1 = Long.valueOf(1);
    private static final String VENDOR1_NAME = "Exotic Fruits Company";
    
    private static final Long ID_2 = Long.valueOf(2);
    private static final String VENDOR2_NAME = "Vegas Cucumbers";
    
    @MockBean
    VendorService vendorService;
    
    @Autowired
    MockMvc mockMvc;

    VendorDTO vendor1;
    VendorDTO vendor2;
    
    @BeforeEach
    void setUp() throws Exception {
        vendor1 = new VendorDTO();
        vendor1.setId(ID_1);
        vendor1.setName(VENDOR1_NAME);
        
        vendor2 = new VendorDTO();
        vendor2.setId(ID_2);
        vendor2.setName(VENDOR2_NAME);
    }

    @Test
    void testMocks() {
        assertNotNull(vendorService);
    }

    @Test
    void testFindAll() throws Exception {
        VendorListDTO vendorList = new VendorListDTO(Arrays.asList(vendor1, vendor2)); 
        
        given(vendorService.findAll()).willReturn(vendorList);
        
        mockMvc.perform(get(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    void testFindById() throws Exception {        
        given(vendorService.findById(anyLong())).willReturn(vendor1);
        
        mockMvc.perform(get(VendorController.BASE_URL +  "/" + ID_1)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(ID_1.intValue())))
        .andExpect(jsonPath("$.name", equalTo(VENDOR1_NAME)));
    }
    
    @Test
    void testSaveDTO() throws Exception {
        vendor1.setVendorUrl(VendorController.BASE_URL +  "/" + ID_1);
        
        given(vendorService.saveDTO(any(VendorDTO.class))).willReturn(vendor1);
        mockMvc.perform(post(VendorController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendor1)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name", equalTo(VENDOR1_NAME)))
        .andExpect(jsonPath("$.vendorUrl", equalTo(VendorController.BASE_URL + "/" + ID_1)));
    }

    @Test
    void testUpdateDTO() throws Exception {
        vendor1.setVendorUrl(VendorController.BASE_URL + "/" + ID_1);
        
        given(vendorService.updateDTO(any(Long.class), any(VendorDTO.class))).willReturn(vendor1);
        mockMvc.perform(put(VendorController.BASE_URL +  "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendor1)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(ID_1.intValue())))
        .andExpect(jsonPath("$.name", equalTo(VENDOR1_NAME)))
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
        
        given(vendorService.patchDTO(any(Long.class), any(VendorDTO.class))).willReturn(returnDTO);
        
        mockMvc.perform(patch(VendorController.BASE_URL + "/1")
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
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
        
        verify(vendorService, times(1)).deleteById(anyLong());
        then(vendorService).should().deleteById(anyLong());
    }

    @Test
    void testFindByIdNotFound() throws Exception {
        given(vendorService.findById(anyLong())).willThrow(ResourceNotFoundException.class);
        
        mockMvc.perform(get(VendorController.BASE_URL + "/1")
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
