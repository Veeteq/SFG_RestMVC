package com.wojnarowicz.sfg.gw.api.controller;

import lombok.Getter;

@Getter
public enum HeaderConstants {

    Recipient("KIAS"),
    MessageType("REQUEST"),
    JMSPriority("1"),
    JMSMessageID(null),
    JMSCorrelationID(null),
    EventCode("ExpectedPayCreate"),
    JMSXGroupID("1"),
    JMSXGroupSeq("1"),
    Originator(null);
    
    private String value;

    private HeaderConstants(String value) {
        this.value = value;
    }
}
