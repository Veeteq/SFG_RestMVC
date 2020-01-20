package com.wojnarowicz.sfg.gw.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;

import com.wojnarowicz.sfg.gw.utils.BsoStatusConverter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="bso_documents", uniqueConstraints = {@UniqueConstraint(columnNames = {"bso_series", "bso_number", "bso_type"}, name = "fk_bso_series_number_type_index_1")})
@AttributeOverride(name="id", column=@Column(name="bso_document_id"))
@SequenceGenerator(name="default_seq", sequenceName="bso_seq", allocationSize=1)
public class BsoDocument extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name="bso_series", nullable = false)
    private String series;
    
    @Column(name="bso_number", nullable = false)
    private String number;
    
    @Column(name="bso_type", nullable = false)
    private String type;
    
    @Column(name="bso_status", nullable = false)
    @Convert(converter = BsoStatusConverter.class)
    private BsoStatus status;

    @Column(name="update_date", columnDefinition="TIMESTAMP", nullable=false)
    @CreationTimestamp
    private LocalDateTime updateDate;
    
    @ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="agent_id", nullable=false)
    private Agent agent;

    @ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="policy_id", nullable=true)
    private Contract contract;
    
    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, mappedBy = "bsoDocument")
    private List<BsoIssuance> bsoIssuances = new ArrayList<>();

    public void addBsoIssuance(BsoIssuance bsoIssuance) {
        bsoIssuance.setBsoDocument(this);
        this.bsoIssuances.add(bsoIssuance);
    }
}