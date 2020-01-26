package com.wojnarowicz.sfg.gw.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(name = "bc_expected_payments_agents",
               joinColumns = @JoinColumn(name = "agent_id"),
               inverseJoinColumns = @JoinColumn(name = "expected_payment_id", columnDefinition = "varchar"))
    private List<Agent> agents = null;
    
    @OneToMany(mappedBy = "expectedPayment", cascade = {CascadeType.ALL})
    //@JoinColumn(name="expected_payment_id", nullable=false)
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
}
