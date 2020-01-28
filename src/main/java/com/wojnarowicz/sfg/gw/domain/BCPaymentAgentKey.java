package com.wojnarowicz.sfg.gw.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class BCPaymentAgentKey implements Serializable {

    private static final long serialVersionUID = -5766604530218744775L;

    @Column(name = "expected_payment_id")
    String expectedPaymentId;
    
    @Column(name = "agent_code")
    Long agentCode;
}
