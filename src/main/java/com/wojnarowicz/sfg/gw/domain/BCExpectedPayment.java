package com.wojnarowicz.sfg.gw.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bc_expected_payments")
public class BCExpectedPayment {

    @Id
    @Column(name = "public_id")
    private String publicId;
    
    @Column(name = "policy_id")
    private String policyId;
    
    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.REFRESH}, mappedBy = "expectedPayment")
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
    private Integer paymentStatus;
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
}
