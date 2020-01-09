package com.wojnarowicz.sfg.restmvc.service;

import java.util.List;

import com.wojnarowicz.sfg.restmvc.api.v1.model.VendorDTO;

public interface VendorService {

	List<VendorDTO> findAll();
	
	VendorDTO findByName(String name);

    VendorDTO findById(Long id);

    VendorDTO saveDTO(VendorDTO vendorDTO);

    VendorDTO updateDTO(Long id, VendorDTO vendorDTO);

    VendorDTO patchDTO(Long id, VendorDTO vendorDTO);
    
    void deleteById(Long id);
}
