package com.wojnarowicz.sfg.gw.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bc_coverages")
public class BCCoverage {
    
    @Id
    @Column(name = "public_id")
    private String chargeItemId;
    
    @ManyToOne
    @JoinColumn(name="expected_payment_id", referencedColumnName="public_id", nullable=false, updatable=false )
    private BCExpectedPayment expectedPayment;
    
    private BigDecimal chargeAmount;
    
    private String chargeCode;
    
    @Enumerated(value = EnumType.STRING)
    private PCCurrency chargeCurrency;
}
