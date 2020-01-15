package com.wojnarowicz.sfg.gw.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Summary",
    "Systems"
})
public class ResponseNotificationDTO {

    @JsonProperty(value = "Summary")
    private ResponseSummaryDTO summary;

    @JsonProperty(value = "Systems")
    private ResponseSystemsDTO systems;
}
