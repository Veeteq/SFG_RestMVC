package com.wojnarowicz.sfg.gw.domain;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "esb_headers")
public class ESBHeader {

    @Id
    @Type(type="uuid-char")
    @Column(name = "jms_correlation_id", columnDefinition = "uuid", nullable = false, updatable = false)
    private UUID jmsCorrelationId;
    
    @Column(name = "jms_message_id")
    private UUID jmsMessageID;
    
    @Column(name = "jms_priority")
    private Integer JMSPriority;

    @Column(name = "event_code")
    private String eventCode;

    @Column(name = "messsage_type")
    private String messageType;
    
    @Column(name = "originator")
    private String orginator;
    
    @Column(name = "recipient")
    private String recipient;
    
    @ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "kias_expected_payment_id", nullable=false)
    private KiasExpectedPayment kiasExpectedPayment;
}
