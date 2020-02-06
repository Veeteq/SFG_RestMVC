package com.wojnarowicz.sfg.gw.domain;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class PCAddress {

    private String addressType;
    private String addressCity;
    private String addressCountry;
    private String addressHousing;
    private String addressStreet;
    private String addressStreetType;
}
