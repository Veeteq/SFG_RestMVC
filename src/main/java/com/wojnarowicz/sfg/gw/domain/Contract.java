package com.wojnarowicz.sfg.gw.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
public class Contract extends NumericEntity {
    private static final long serialVersionUID = 1L;

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, mappedBy = "contract")
    private List<BsoDocument> bsoDocuments = new ArrayList<BsoDocument>();
    
    @Column(name = "policy_series")
    private String series;
    
    @Column(name = "policy_number")
    private String number;

    @Column(name = "policy_premium")
    private BigDecimal premium;

    @Column(name = "due_date", columnDefinition="TIMESTAMP", nullable=true)
    private LocalDateTime dueDate;

     public void addBsoDocument(BsoDocument bsoDocument) {
        bsoDocument.setContract(this);
        this.bsoDocuments.add(bsoDocument);
    }
}