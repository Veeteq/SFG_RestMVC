package com.wojnarowicz.sfg.gw.api.model.esb;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PCContactMailDTO {

    @JsonProperty("Address")
    private String mailAddress;

    @JsonProperty("MailType")
    private String mailType;
}
