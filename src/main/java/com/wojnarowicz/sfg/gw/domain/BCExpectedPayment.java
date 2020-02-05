package com.wojnarowicz.sfg.gw.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.wojnarowicz.sfg.gw.converter.BCPaymentStatusConverter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bc_expected_payments")
@AttributeOverride(name="id", column=@Column(name="public_id"))
public class BCExpectedPayment extends StringEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "policy_id")
    private String policyId;
    
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "expectedPayment")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<BCPaymentAgent> paymentAgents = new ArrayList<>();
    
    @OneToMany(mappedBy = "expectedPayment", cascade = {CascadeType.ALL})
    private List<BCCoverage> coverages = null;
    
    private LocalDate expectedPaymentDate;
    private BigDecimal paymentAmount;
    
    @Enumerated(value = EnumType.STRING)
    private PCCurrency paymentCurrency;
    
    private LocalDate paymentDateOnAccountRGS;
    private LocalDate lastPaymentDate;
    private BigDecimal yetPayInsurancePremium;
    
    @Column(name = "payment_status")
    @Convert(converter = BCPaymentStatusConverter.class)
    private BCPaymentStatus paymentStatus;
    
    private String typeOfOperation;
    private String productCode;
    private String subProductCode;
    private Integer defaultBaseState;
    private LocalDate statementDate;
    private String statementNumber;
    private Integer termNumber;
    
    public void addAgentRole(BCPaymentAgent paymentAgent) {
        paymentAgent.setExpectedPayment(this);
        this.paymentAgents.add(paymentAgent);
    }

    public void addAgentRole(Agent savedAgent, AgentRole agentRole) {
        BCPaymentAgent paymentAgent = new BCPaymentAgent(this, savedAgent, agentRole);
        this.paymentAgents.add(paymentAgent);
    }
}
