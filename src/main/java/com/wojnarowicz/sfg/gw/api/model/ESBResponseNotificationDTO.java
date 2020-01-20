package com.wojnarowicz.sfg.gw.api.model;

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
public class ESBResponseNotificationDTO {

    @JsonProperty(value = "Summary")
    private ESBResponseSummaryDTO summary;

    @JsonProperty(value = "Systems")
    private ESBResponseSystemsDTO systems;
}
