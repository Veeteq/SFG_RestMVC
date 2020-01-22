package com.wojnarowicz.sfg.gw.api.model.esb;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "AddressType",
    "City",
    "Country",
    "Housing",
    "Street",
    "StreetType"
})
public class PCAddressDTO {

    @JsonProperty("AddressType")
    public String addressType;
    
    @JsonProperty("City")
    public String addressCity;
    
    @JsonProperty("Country")
    public String addressCountry;
    
    @JsonProperty("Housing")
    public String addressHousing;
    
    @JsonProperty("Street")
    public String addressStreet;
    
    @JsonProperty("StreetType")
    public String addressStreetType;

}