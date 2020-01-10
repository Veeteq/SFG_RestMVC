package com.wojnarowicz.sfg.restmvc.service;

import com.wojnarowicz.sfg.restmvc.api.v1.model.VendorDTO;
import com.wojnarowicz.sfg.restmvc.api.v1.model.VendorListDTO;

public interface VendorService {

	VendorListDTO findAll();
	
	VendorDTO findByName(String name);

    VendorDTO findById(Long id);

    VendorDTO saveDTO(VendorDTO vendorDTO);

    VendorDTO updateDTO(Long id, VendorDTO vendorDTO);

    VendorDTO patchDTO(Long id, VendorDTO vendorDTO);
    
    void deleteById(Long id);
}
