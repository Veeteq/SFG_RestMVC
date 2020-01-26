package com.wojnarowicz.sfg.gw.api.model.kias;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ReportCreationDate",
    "TotalAmount",
    "ReportNumber",
    "RetentionKv",
    "RefactNumber",
    "AgrId",
    "StartDate",
    "EndDate",
    "ActExternalId",
    "Kv_Amt",
    "Commissions"
})
public class KiasPartnerDTO {

    @JsonProperty("ReportCreationDate")
    private String reportCreationDate;
   
    @JsonProperty("TotalAmount")
    private String totalAmount;
    
    @JsonProperty("ReportNumber")
    private String reportNumber;
    
    @JsonProperty("RetentionKv")
    private Integer retentionKv;
    
    @JsonProperty("RefactNumber")
    private String refactNumber;
    
    @JsonProperty("AgrId")
    private String agrId;
    
    @JsonProperty("StartDate")
    private String startDate;
    
    @JsonProperty("EndDate")
    private String endDate;
    
    @JsonProperty("ActExternalId")
    private String actExternalId;
    
    @JsonProperty("Kv_Amt")
    private String kvAmt;
    
    @JsonProperty("Commissions")
    private List<KiasPartnerCommisionDTO> commissions = null;
}
