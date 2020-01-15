package com.wojnarowicz.sfg.gw.domain;

import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="bso_documents", uniqueConstraints = {@UniqueConstraint(columnNames = {"bso_series", "bso_number"}, name = "UK_BSO_SERIES_NUMBER_INDEX_1")})
@AttributeOverride(name="id", column=@Column(name="bso_document_id"))
@SequenceGenerator(name="default_seq", sequenceName="bso_seq", allocationSize=1)
public class BsoDocument extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name="BSO_SERIES")
    private String series;
    
    @Column(name="BSO_NUMBER")
    private String number;
    
    @Column(name="TYPE")
    private String type;
    
    @Column(name="STATUS")
    @Convert(converter = BsoStatusConverter.class)
    private BsoStatus status;

    @Column(name="UPDATE_DATE", columnDefinition="TIMESTAMP", nullable=false)
    @CreationTimestamp
    private LocalDateTime updateDate;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="AGENT_ID", nullable=false)
    private Agent agent;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="POLICY_ID", nullable=true)
    private Contract contract;
}