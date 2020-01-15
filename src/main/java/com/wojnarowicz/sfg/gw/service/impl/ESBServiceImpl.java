package com.wojnarowicz.sfg.gw.service.impl;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojnarowicz.sfg.gw.api.controller.HeaderConstants;
import com.wojnarowicz.sfg.gw.api.model.ResponseRootDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasRequestDTO;
import com.wojnarowicz.sfg.gw.api.model.sap.SapRequestDTO;
import com.wojnarowicz.sfg.gw.domain.ESBHeader;
import com.wojnarowicz.sfg.gw.domain.KiasExpectedPayment;
import com.wojnarowicz.sfg.gw.repository.KiasRepository;
import com.wojnarowicz.sfg.gw.service.ESBService;

@Service
public class ESBServiceImpl implements ESBService {

    private KiasRepository kiasRepository;
    
    @Autowired
    public ESBServiceImpl(KiasRepository kiasRepository) {
        this.kiasRepository = kiasRepository;
    }

    @Override
    public ResponseRootDTO process(Map<String, String> headerMap, KiasRequestDTO requestData) {
        
        ESBHeader esbHeader = new ESBHeader();
        esbHeader.setJmsCorrelationId(UUID.fromString(headerMap.get(HeaderConstants.JMSCorrelationID.name().toLowerCase())));
        esbHeader.setEventCode(headerMap.get(HeaderConstants.EventCode.name()));
        esbHeader.setJmsMessageID(UUID.fromString(headerMap.get(HeaderConstants.JMSMessageID.name().toLowerCase())));
        esbHeader.setJMSPriority(Integer.valueOf(headerMap.get(HeaderConstants.JMSPriority.name())));
        esbHeader.setMessageType(headerMap.get(HeaderConstants.MessageType.name()));
        esbHeader.setOrginator(headerMap.get(HeaderConstants.Originator.name()));
        esbHeader.setRecipient(headerMap.get(HeaderConstants.Recipient.name()));
        
        KiasExpectedPayment kiasExpectedPayment = new KiasExpectedPayment();
        kiasExpectedPayment.setBcPublicId(requestData.getExpectedPaymentId());
        kiasExpectedPayment.addEsbHeader(esbHeader);
        
        KiasExpectedPayment savedKiasExpectedPayment = kiasRepository.save(kiasExpectedPayment);
        
        return null; //
    }

    @Override
    public void processSapRequest(SapRequestDTO sapRequestData) {
        
    }
}
