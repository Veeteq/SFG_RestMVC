package com.wojnarowicz.sfg.gw.adapter;

import java.util.Map;

import com.wojnarowicz.sfg.gw.api.controller.HeaderConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ESBProcessingAdapterFactory {

    public static ESBProcessingAdapter buildESBProcessingAdapterFactory(Map<String, String> headerMap) {
        
        String eventCode = headerMap.get(HeaderConstants.EventCode.name().toLowerCase());
        String recipient = headerMap.get(HeaderConstants.Recipient.name().toLowerCase());
        
        log.info(eventCode);
        log.info(recipient);
        
        return null;
    }
}
