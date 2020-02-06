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
    "Detail"
})
public class ESBResponseDetailsDTO {

    @JsonProperty(value = "Detail")
    private List<ESBResponseDetailDTO> detail = new ArrayList<ESBResponseDetailDTO>();

    public void addDetail(ESBResponseDetailDTO _detail) {
        this.detail.add(_detail);
    }
}
