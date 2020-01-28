package com.wojnarowicz.sfg.gw.domain;

import java.util.Map;
import java.util.TreeMap;

import lombok.Getter;

@Getter
public enum AgentRole {
    CREATOR(4),
    ATTRACTOR(5),
    PAYMENTRECEIVER(6),
    PLANNER(7);
    
    private static final Map<Integer, AgentRole> lookup = new TreeMap<Integer, AgentRole>();
    
    static {
        for (AgentRole el : AgentRole.values()) {
            lookup.put(el.getCode(), el);
        }
    }
    
    private final int code;

    private AgentRole(int code) {
        this.code = code;
    }
    
    public static AgentRole getByCode(int code) {
        return lookup.get(code);
    }
}
