package com.wojnarowicz.sfg.gw.api.model.esb;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Code",
    "PhoneNumber",
    "PhoneType"
})
public class PCContactPhoneDTO {

    @JsonProperty("Code")
    public String phoneCode;

    @JsonProperty("PhoneNumber")
    public String phoneNumber;

    @JsonProperty("PhoneType")
    public String phoneType;

}
