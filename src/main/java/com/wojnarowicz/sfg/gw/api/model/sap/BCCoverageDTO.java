package com.wojnarowicz.sfg.gw.api.model.sap;

import java.math.BigDecimal;

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
public class BCCoverageDTO {

    @JsonProperty("ChargeAmount")
    public BigDecimal chargeAmount;
    
    @JsonProperty("ChargeCode")
    public String chargeCode;
    
    @JsonProperty("ChargeCurrency")
    public String chargeCurrency;
    
    @JsonProperty("ChargeItemID")
    public String chargeItemId;
}
