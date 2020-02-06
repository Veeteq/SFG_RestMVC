package com.wojnarowicz.sfg.restmvc.api.v1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VendorDTO {
    private Long id;
    private String name;
    private String vendorUrl;
}
