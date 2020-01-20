package com.wojnarowicz.sfg.gw.domain;

import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bso_issuances")
@AttributeOverride(name="id", column=@Column(name="bso_issuance_id"))
@SequenceGenerator(name="default_seq", sequenceName="bso_issuance_seq", allocationSize=1)
public class BsoIssuance extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="bso_id", nullable=false)
    private BsoDocument bsoDocument;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="agent_id", nullable=false)
    private Agent agent;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="policy_id", nullable=true)
    private Contract contract;

    @Column(name="issue_date", columnDefinition="DATE", nullable=false)
    @CreationTimestamp  
    private LocalDateTime issueDate;

    @Column(name="bso_status")
    @Enumerated(value = EnumType.STRING)
    private BsoStatus status = BsoStatus.NEW;
}
