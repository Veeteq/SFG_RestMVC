package com.wojnarowicz.sfg.gw.domain;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bc_coverages")
@AttributeOverride(name="id", column=@Column(name="public_id"))
public class BCCoverage extends StringEntity {
    private static final long serialVersionUID = 1L;

    private BigDecimal chargeAmount;
    
    private String chargeCode;
    
    @Enumerated(value = EnumType.STRING)
    private PCCurrency chargeCurrency;

    @ManyToOne
    @JoinColumn(name="expected_payment_id", referencedColumnName="public_id", nullable=false, updatable=false )
    private BCExpectedPayment expectedPayment;    
}
