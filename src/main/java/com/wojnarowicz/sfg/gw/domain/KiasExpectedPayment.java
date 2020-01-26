package com.wojnarowicz.sfg.gw.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "kias_expected_payments")
@NamedQueries({
    @NamedQuery(name = "ExpectedPayments.findAll", query = "select p from KiasExpectedPayment p")
})
public class KiasExpectedPayment {

    @Id
    @Column(name = "public_id")
    private String publicId;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "kiasExpectedPayment")
    @NotFound(action = NotFoundAction.IGNORE)
    private Set<ESBHeader> esbHeaders = new HashSet<ESBHeader>();
    
    @Column(name = "payment_number")
    private String expectedPaymentNum;
    
    @Column(name = "due_date")
    private String expectedPaymentDate;
    
    @Column(name = "skk_code")
    private String skkCode;
    
    @Column(name = "currency")
    private String currency;
    
    @Column(name = "form_id")
    private String formId;
    
    @Column(name = "payment_method")
    private String methodPayId;
    private String comment;
    private BigDecimal amount;
    private BigDecimal amountRub;
    private String payerType;
    private String payerJur;
    private String payerInn;
    private String payerFullName;
    private String lnr;
    //private List<Contract> contracts;
    //private Confirmation confirmations;
    private String agrId;
    private String payerKpp;
    
    @Enumerated(value = EnumType.STRING)
    private GWKiasPaymentStatus paymentStatus = GWKiasPaymentStatus.REGISTERED;
    
    public void addEsbHeader(ESBHeader esbHeader) {
        esbHeader.setKiasExpectedPayment(this);
        this.esbHeaders.add(esbHeader);
    }
}