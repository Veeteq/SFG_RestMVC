package com.wojnarowicz.sfg.restmvc.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wojnarowicz.sfg.restmvc.api.v1.model.VendorDTO;
import com.wojnarowicz.sfg.restmvc.api.v1.model.VendorListDTO;
import com.wojnarowicz.sfg.restmvc.service.VendorService;

@RestController()
@RequestMapping(path = VendorController.BASE_URL)
public class VendorController {
	public static final String BASE_URL = "/api/v1/vendors";
	
	private final VendorService vendorService;
	
	@Autowired
	public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public VendorListDTO getAll() {
	    return vendorService.findAll();
	}
	
	@GetMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public VendorDTO getById(@PathVariable(name = "id") String id) {
	    Long longId = Long.valueOf(id);
	    return vendorService.findById(longId);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public VendorDTO saveVendor(@RequestBody VendorDTO vendorDTO) {
	    return vendorService.saveDTO(vendorDTO);
	}
	
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteById(@PathVariable(name = "id") String id) {
	    Long longId = Long.valueOf(id);
	    vendorService.deleteById(longId);
	}
	
    @PutMapping(path = "/{id}", consumes="application/json", produces="application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public VendorDTO updateVendor(@PathVariable(name = "id") String id, @RequestBody VendorDTO vendorDTO) {
        Long longId = Long.valueOf(id);
        vendorDTO.setId(longId);
        return vendorService.updateDTO(longId, vendorDTO);
    }

    @PatchMapping(path = "/{id}", consumes="application/json", produces="application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable(name = "id") String id, @RequestBody VendorDTO vendorDTO) {
        Long longId = Long.valueOf(id);
        vendorDTO.setId(longId);
        return vendorService.patchDTO(longId, vendorDTO);
    }
}
