package com.wojnarowicz.sfg.restmvc.api.v1.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorListDTO {

    private List<VendorDTO> vendors;
}
