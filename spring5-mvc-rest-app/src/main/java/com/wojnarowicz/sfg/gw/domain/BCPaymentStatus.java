package com.wojnarowicz.sfg.gw.domain;

import java.util.Map;
import java.util.TreeMap;

import lombok.Getter;

@Getter
public enum BCPaymentStatus {

    NOTPAID(0),
    PAID(1);

    private int id;

    private static final Map<Integer, BCPaymentStatus> lookup = new TreeMap<Integer, BCPaymentStatus>();
    
    static {
        for (BCPaymentStatus status : BCPaymentStatus.values()) {
            lookup.put(status.getId(), status);
        }
    }

    private BCPaymentStatus(int id) {
        this.id = id;
    }

    public static BCPaymentStatus getById(int id) {
        return lookup.get(id);
    }    
}
