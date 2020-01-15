package com.wojnarowicz.sfg.gw.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "kias_expected_payments")
public class KiasExpectedPayment {

    @Id
    @Column(name = "expected_payment_id")
    private String bcPublicId;

    @OneToMany(mappedBy = "kiasExpectedPayment")    
    private List<ESBHeader> esbHeaders = new ArrayList<ESBHeader>();
    
    @Column(name = "payment_number")
    private String expectedPaymentNum;
    
    @Column(name = "due_date")
    private String expectedPaymentDate;
    
    private String skkCode;
    private String currency;
    private String formId;
    private String methodPayId;
    private String comment;
    private String amount;
    private String amountRub;
    private String payerType;
    private String payerJur;
    private String payerInn;
    private String payerFullName;
    private String lnr;
    //private String contracts;
    //private String confirmations;
    private String agrId;
    private String payerKpp;
    
    public void addEsbHeader(ESBHeader esbHeader) {
        esbHeader.setKiasExpectedPayment(this);
        this.esbHeaders.add(esbHeader);
    }
}