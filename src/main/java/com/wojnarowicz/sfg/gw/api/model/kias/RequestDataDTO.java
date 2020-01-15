package com.wojnarowicz.sfg.gw.api.model.kias;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"params",
"EXPPAYMENTID"
})
public class RequestDataDTO {

    @JsonProperty("params")
    public ParamsDTO params;
    
    @JsonProperty("EXPPAYMENTID")
    public String expectedPaymentId;
}
