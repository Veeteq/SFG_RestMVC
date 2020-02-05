package com.wojnarowicz.sfg.gw.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class BCPaymentAgentKey implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "expected_payment_id")
    String expectedPaymentId;
    
    @Column(name = "agent_code")
    Long agentCode;
    
    @Enumerated(value = EnumType.STRING)
    AgentRole agentRole;
    
    @SuppressWarnings("unused")
    private BCPaymentAgentKey() {}
    
    public BCPaymentAgentKey(String expectedPaymentId, Long agentCode, AgentRole agentRole) {
        this.expectedPaymentId = expectedPaymentId;
        this.agentCode = agentCode;
        this.agentRole = agentRole;
    }        

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        BCPaymentAgentKey that = (BCPaymentAgentKey) o;
        return Objects.equals(expectedPaymentId, that.expectedPaymentId) &&
               Objects.equals(agentCode, that.agentCode) &&
               Objects.equals(agentRole, that.agentRole);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(expectedPaymentId, agentCode, agentRole);
    }
}
