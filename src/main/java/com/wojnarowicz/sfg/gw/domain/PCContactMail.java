package com.wojnarowicz.sfg.gw.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class PCContactMail {

    @Column(name = "mail_address")
    private String mailAddress;
    
    @Column(name = "mail_type")
    private String mailType;
}
