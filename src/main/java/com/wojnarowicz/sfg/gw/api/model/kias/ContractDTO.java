package com.wojnarowicz.sfg.gw.api.model.kias;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"CONTRACTNUM",
"CONTRACTDATE",
"CONTRACTAMOUNT",
"CONTRACTISSUER",
"CONTRACTPRODUCT",
"POLICYPUBLICID"
})
public class ContractDTO {

    @JsonProperty("CONTRACTNUM")
    public String number;
    
    @JsonProperty("CONTRACTDATE")
    public String date;
    
    @JsonProperty("CONTRACTAMOUNT")
    public String amount;
    
    @JsonProperty("CONTRACTISSUER")
    public String issuer;
    
    @JsonProperty("CONTRACTPRODUCT")
    public Object product;
    
    @JsonProperty("POLICYPUBLICID")
    public String policyPublicId;
}
