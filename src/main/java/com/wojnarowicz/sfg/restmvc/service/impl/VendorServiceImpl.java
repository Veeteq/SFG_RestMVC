package com.wojnarowicz.sfg.restmvc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojnarowicz.sfg.restmvc.api.v1.controller.VendorController;
import com.wojnarowicz.sfg.restmvc.api.v1.mapper.VendorMapper;
import com.wojnarowicz.sfg.restmvc.api.v1.model.VendorDTO;
import com.wojnarowicz.sfg.restmvc.domain.Vendor;
import com.wojnarowicz.sfg.restmvc.exception.ResourceNotFoundException;
import com.wojnarowicz.sfg.restmvc.repositories.VendorRepository;
import com.wojnarowicz.sfg.restmvc.service.VendorService;

@Service
public class VendorServiceImpl implements VendorService {

	private final VendorRepository vendorRepository;
	private final VendorMapper vendorMapper;

	@Autowired
	public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
		this.vendorRepository = vendorRepository;
		this.vendorMapper = vendorMapper;
	}

	@Override
 	public List<VendorDTO> findAll() {
		return vendorRepository.findAll()
		        .stream()
		        .map(vendorMapper::vendorToVendorDTO)
		        .collect(Collectors.toList());
	}

	@Override
	public VendorDTO findByName(String name) {
		return vendorRepository.findByName(name)
		.map(vendor -> {
			VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
			return vendorDTO;
		})
		.orElseThrow(ResourceNotFoundException::new);
	}

	@Override
	public VendorDTO findById(Long id) {
		return vendorRepository.findById(id)
				.map(vendor -> {
					VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
					return vendorDTO;
				})
				.orElseThrow(ResourceNotFoundException::new);
	}

	@Override
	public VendorDTO saveDTO(VendorDTO vendorDTO) {
		Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        return saveAndConvertVendor(vendor);
	}

	@Override
	public VendorDTO updateDTO(Long id, VendorDTO vendorDTO) {
		Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
		vendor.setId(id);
		return saveAndConvertVendor(vendor);
	}

	@Override
	public VendorDTO patchDTO(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id).map(vendor -> {
            if(vendorDTO.getName() != null){
            	vendor.setName(vendorDTO.getName());
            }

            if(vendorDTO.getName() != null){
            	vendor.setName(vendorDTO.getName());
            }
            
            return saveAndConvertVendor(vendor);
        }).orElseThrow(ResourceNotFoundException::new);
    }
	
	@Override
	public void deleteById(Long id) {
		vendorRepository.deleteById(id);
	}
	
    private VendorDTO saveAndConvertVendor(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO savedVendorDTO = vendorMapper.vendorToVendorDTO(savedVendor);
        savedVendorDTO.setVendorUrl(getVendorUrl(savedVendor.getId()));
        return savedVendorDTO;
    }

    private String getVendorUrl(Long id) {
        return VendorController.BASE_URL + "/" + id;
    }
}
