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
    "Detail"
})
public class ESBResponseDetails {

    @JsonProperty(value = "Detail")
    private List<ESBResponseDetail> detail = new ArrayList<ESBResponseDetail>();

    public void addDetail(ESBResponseDetail _detail) {
        this.detail.add(_detail);
    }
}
