package com.wojnarowicz.sfg.gw.domain;

import lombok.Getter;

@Getter
public enum ESBEventType {
    D("ExpectedPayDelete"),
    I("ExpectedPayCreate");

    private String description;

    private ESBEventType(String description) {
        this.description = description;
    }
}
