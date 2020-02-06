package com.wojnarowicz.sfg.gw.api.model.sap;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Id",
    "ActDate",
    "RefactNumber",
    "AgentId",
    "AgrId",
    "RetentionKv",
    "Commissions"
})
public class ActOfPerformanceDTO {

    @JsonProperty("Id")
    private String id;
    
    @JsonProperty("ActDate")
    private String actDate;
    
    @JsonProperty("RefactNumber")
    private String refactNumber;
    
    @JsonProperty("AgentId")
    private String agentId;

    @JsonProperty("AgrId")
    private String agrId;
    
    @JsonProperty("RetentionKv")
    private Integer retentionKv;
    
    @JsonProperty("Commissions")
    private List<ActOfPerformanceCommissionDTO> commissions = null;
}