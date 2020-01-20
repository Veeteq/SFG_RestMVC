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
    "Component",
    "DateTime",
    "ExtendedDetails",
    "Status"
})
public class ESBResponseDetailDTO {

    @JsonProperty(value = "Component")
    private String component;

    @JsonProperty(value = "DateTime")
    private String dateTime;

    @JsonProperty(value = "ExtendedDetails")
    private ESBResponseExtendedDetailsDTO extendedDetails;

    @JsonProperty(value = "Status")
    private String status;
}
