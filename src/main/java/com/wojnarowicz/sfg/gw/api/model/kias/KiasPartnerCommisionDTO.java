package com.wojnarowicz.sfg.gw.api.model.kias;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "PolicyId",
    "PaymentId",
    "ChargeItemId",
    "ReportSystemCode",
    "Commission_Amt",
    "Commission_Cur",
    "ComissionTypeSap"
})
public class KiasPartnerCommisionDTO {

    @JsonProperty("PolicyId")
    private String policyId;
    
    @JsonProperty("PaymentId")
    private String paymentId;
    
    @JsonProperty("ChargeItemId")
    private String chargeItemId;
    
    @JsonProperty("ReportSystemCode")
    private String reportSystemCode;
    
    @JsonProperty("Commission_Amt")
    private String commissionAmt;
    
    @JsonProperty("Commission_Cur")
    private String commissionCur;
    
    @JsonProperty("ComissionTypeSap")
    private String comissionTypeSap;
}
