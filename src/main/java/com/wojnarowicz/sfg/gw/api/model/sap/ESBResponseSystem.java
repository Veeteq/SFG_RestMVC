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
    "SysName",
    "GeneralStatus",
    "Details"
})
public class ESBResponseSystem {

    @JsonProperty(value = "SysName")
    private String sysName;

    @JsonProperty(value = "GeneralStatus")
    private String generalStatus;

    @JsonProperty(value = "Details")
    private ESBResponseDetails details;
}
