package com.wojnarowicz.sfg.restmvc.api.v1.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wojnarowicz.sfg.restmvc.api.v1.model.VendorDTO;
import com.wojnarowicz.sfg.restmvc.domain.Vendor;

class VendorMapperTest {

    private static final long ID_1 = 1L;
    private static final long ID_2 = 2L;
    private static final String VENDOR_NAME = "Fun Fresh Fruits Ltd";
    VendorMapper mapper = VendorMapper.INSTANCE;
    
    Vendor vendor;
    VendorDTO vendorDTO;
    
    @BeforeEach
    void setUp() throws Exception {
        vendor = new Vendor();
        vendor.setId(ID_1);
        vendor.setName(VENDOR_NAME);

        vendorDTO = new VendorDTO();
        vendorDTO.setId(ID_2);
        vendorDTO.setName(VENDOR_NAME);
    }

    @Test
    void testVendorToVendorDTO() {
        VendorDTO vendorDTO = mapper.vendorToVendorDTO(vendor);
        assertNotNull(vendorDTO);
        assertEquals(ID_1, vendorDTO.getId());
        assertEquals(VENDOR_NAME, vendorDTO.getName());
    }
    
    @Test
    void testVendorDTOToVendor() {
        Vendor newVendor = mapper.vendorDTOToVendor(vendorDTO);
        assertNotNull(newVendor);
        assertEquals(ID_2, newVendor.getId());
        assertEquals(VENDOR_NAME, newVendor.getName());
    }
}
