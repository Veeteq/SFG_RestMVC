package com.wojnarowicz.sfg.gw.domain;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class PCContactPhone {

    private String phoneCode;
    private String phoneNumber;
    private String phoneType;
}
