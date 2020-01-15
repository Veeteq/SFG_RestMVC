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
    "ExtendedDetail"
})
public class ESBResponseExtendedDetails {

    @JsonProperty(value = "ExtendedDetail")
    private List<ESBResponseExtendedDetail> extendedDetail = new ArrayList<ESBResponseExtendedDetail>();

    public void addExtendedDetail(ESBResponseExtendedDetail _extendedDetail) {
        this.extendedDetail.add(_extendedDetail);
    }
}
