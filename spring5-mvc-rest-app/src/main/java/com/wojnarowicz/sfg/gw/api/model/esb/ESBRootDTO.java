package com.wojnarowicz.sfg.gw.api.model.esb;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"PCPolicy"
})
public class ESBRootDTO {

    @JsonProperty("PCPolicy")
    public PCPolicyDTO policyDTO;

}
