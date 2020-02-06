package com.wojnarowicz.sfg.gw.api.model;

import java.util.ArrayList;
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
public class ESBResponseSystemsDTO {

    @JsonProperty(value = "System")
    private List<ESBResponseSystemDTO> system = new ArrayList<ESBResponseSystemDTO>();

    public void addSystem(ESBResponseSystemDTO _system) {
        this.system.add(_system);
    }
}
