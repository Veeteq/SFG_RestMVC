package com.wojnarowicz.sfg.gw.api.model.sap;

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
public class ESBResponseSystems {

    @JsonProperty(value = "System")
    private List<ESBResponseSystem> system = new ArrayList<ESBResponseSystem>();

    public void addSystem(ESBResponseSystem _system) {
        this.system.add(_system);
    }
}
