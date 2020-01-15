package com.wojnarowicz.sfg.gw.api.model.sap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"ChargeAmount",
"ChargeCode",
"ChargeCurrency",
"ChargeItemID"
})
public class CoverageDTO {

    @JsonProperty("ChargeAmount")
    public Float chargeAmount;
    
    @JsonProperty("ChargeCode")
    public String chargeCode;
    
    @JsonProperty("ChargeCurrency")
    public String chargeCurrency;
    
    @JsonProperty("ChargeItemID")
    public String chargeItemID;
}
