package com.wojnarowicz.sfg.gw.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "System"
})
public class ResponseSystemsDTO {

    @JsonProperty(value = "System")
    private List<ResponseSystemDTO> systems;
}
