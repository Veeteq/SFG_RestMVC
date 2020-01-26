package com.wojnarowicz.sfg.gw.api.model.kias;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.wojnarowicz.sfg.gw.api.model.BCRequestDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"data"
})
public class KiasRootDTO extends BCRequestDTO {

    @JsonProperty("data")
    public KiasDataDTO data;
}
