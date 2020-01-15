package com.wojnarowicz.sfg.gw.api.model.bso;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value = Include.NON_NULL)
public class BusinessData {

    @JsonProperty("bso")
	private BsoDocumentDTO bsoDocument;
    
    @JsonProperty("agent")
	private AgentDTO agent;
    
    @JsonProperty("contract")
	private ContractDTO contract;
    
    @JsonProperty("issueDate")
	private String issueDate;
}
