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
    "Code",
    "Description",
    "Message"
})
public class ESBResponseExtendedDetail {
    @JsonProperty(value = "Code")
    private String code;

    @JsonProperty(value = "Description")
    private String description;

    @JsonProperty(value = "Message")
    private String message;
}
