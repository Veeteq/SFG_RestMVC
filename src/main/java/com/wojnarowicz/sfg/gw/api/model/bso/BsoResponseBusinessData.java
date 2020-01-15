package com.wojnarowicz.sfg.gw.api.model.bso;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value = Include.NON_NULL)
public class BsoResponseBusinessData {

    private String result;
    private String system;
}
