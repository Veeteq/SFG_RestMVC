package com.wojnarowicz.sfg.gw.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "esb_headers")
public class ESBHeader {

    @Id
    @Column(name = "jms_correlation_id")
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
    
    @ManyToOne
    @JoinColumn(name = "kias_expected_payment_id", nullable=false)
    private KiasExpectedPayment kiasExpectedPayment;
}
