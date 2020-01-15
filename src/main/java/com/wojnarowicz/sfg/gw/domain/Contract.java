package com.wojnarowicz.sfg.gw.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contracts")
@AttributeOverride(name="id", column=@Column(name="policy_id"))
@SequenceGenerator(name="default_seq", sequenceName="contract_seq", allocationSize=1)
public class Contract extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "POLICY_SERIES")
    private String series;
    
    @Column(name = "POLICY_NUMBER")
    private String number;

    @Column(name = "POLICY_PREMIUM")
    private BigDecimal premium;

    @Column(name = "DUE_DATE", columnDefinition="TIMESTAMP", nullable=true)
    private LocalDateTime dueDate;
}