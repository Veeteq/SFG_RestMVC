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
"params",
"EXPPAYMENTID"
})
public class KiasRequestDTO {

    @JsonProperty("params")
    private ParamsDTO params;
    
    @JsonProperty("EXPPAYMENTID")
    private String expectedPaymentId;
}
