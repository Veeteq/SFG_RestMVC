package com.wojnarowicz.sfg.gw.domain;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bc_payment_agent_roles")
public class BCPaymentAgent {

    @EmbeddedId
    private BCPaymentAgentKey key;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("expected_payment_id")
    @JoinColumn(name = "expected_payment_id", nullable = false, insertable = true, updatable = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private BCExpectedPayment expectedPayment;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("agent_code")
    @JoinColumn(name = "agent_code", nullable = false, insertable = true, updatable = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private Agent agent;
    
    @MapsId("agent_role")
    @Transient
    @Enumerated(value = EnumType.STRING)
    private AgentRole agentRole;
    
    @SuppressWarnings("unused")
    private BCPaymentAgent() {}
    
    public BCPaymentAgent(BCExpectedPayment expectedPayment, Agent agent, AgentRole agentRole) {
        this.expectedPayment = expectedPayment;
        this.agent = agent;
        this.agentRole = agentRole;
        this.key = new BCPaymentAgentKey(expectedPayment.getId(), agent.getId(), agentRole);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        BCPaymentAgent that = (BCPaymentAgent) o;
        return Objects.equals(expectedPayment, that.expectedPayment) &&
               Objects.equals(agent, that.agent) &&
               Objects.equals(agentRole, that.agentRole);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(expectedPayment, agent, agentRole);
    }    
}
