package com.wojnarowicz.sfg.gw.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

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
    BCPaymentAgentKey key;
    
    @ManyToOne
    @MapsId("expected_payment_id")
    @JoinColumn(name = "expected_payment_id", nullable = false, insertable = true, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    BCExpectedPayment expectedPayment;
    
    @ManyToOne
    @MapsId("agent_code")
    @JoinColumn(name = "agent_code", nullable = false, insertable = true, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    Agent agent;
    
    @Enumerated(value = EnumType.STRING)
    AgentRole agentRole;
}
