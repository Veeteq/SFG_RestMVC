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
    "Summary",
    "Systems"
})
public class ESBResponseNotification {

    @JsonProperty(value = "Summary")
    private ESBResponseSummary summary;

    @JsonProperty(value = "Systems")
    private ESBResponseSystems systems;
}
