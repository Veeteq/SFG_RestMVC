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
    "ExtendedDetail"
})
public class ESBResponseExtendedDetailsDTO {

    @JsonProperty(value = "ExtendedDetail")
    private List<ESBResponseExtendedDetailDTO> extendedDetail = new ArrayList<ESBResponseExtendedDetailDTO>();

    public void addExtendedDetail(ESBResponseExtendedDetailDTO _extendedDetail) {
        this.extendedDetail.add(_extendedDetail);
    }
}
