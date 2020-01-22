package com.wojnarowicz.sfg.gw.adapter;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.wojnarowicz.sfg.gw.api.controller.HeaderConstants;
import com.wojnarowicz.sfg.gw.api.model.ESBResponseRootDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasRequestDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasRootDTO;
import com.wojnarowicz.sfg.gw.domain.ESBHeader;
import com.wojnarowicz.sfg.gw.domain.KiasExpectedPayment;
import com.wojnarowicz.sfg.gw.mapper.KiasMapper;
import com.wojnarowicz.sfg.gw.repository.KiasRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KiasProcessingAdapter {

    private final KiasRepository kiasRepository;
    
    @Autowired
    public KiasProcessingAdapter(KiasRepository kiasRepository) {
        this.kiasRepository = kiasRepository;
    }

    public ESBHeader processHeader(Map<String, String> headerMap) {
        log.info("processHeader");
        ESBHeader esbHeader = new ESBHeader();
        
        esbHeader.setJmsCorrelationId(UUID.fromString(headerMap.get(HeaderConstants.JMSCorrelationID.name().toLowerCase())));
        esbHeader.setEventCode(headerMap.get(HeaderConstants.EventCode.name().toLowerCase()));
        //esbHeader.setJmsMessageID(UUID.fromString(headerMap.get(HeaderConstants.JMSMessageID.name().toLowerCase())));
        esbHeader.setJMSPriority(Integer.valueOf(headerMap.get(HeaderConstants.JMSPriority.name().toLowerCase())));
        esbHeader.setMessageType(headerMap.get(HeaderConstants.MessageType.name().toLowerCase()));
        esbHeader.setOrginator(headerMap.get(HeaderConstants.Originator.name().toLowerCase()));
        esbHeader.setRecipient(headerMap.get(HeaderConstants.Recipient.name().toLowerCase()));
        
        //System.out.println(ESBController.asJsonString(esbHeader));
        return esbHeader;
    }


    public KiasExpectedPayment processBody(KiasRootDTO kiasRootDTO) {
        log.info("processBody");
        
        KiasRequestDTO kiasRequestDTO = kiasRootDTO.getData().getRequest();
        
        KiasExpectedPayment kiasExpectedPayment = KiasMapper.INSTANCE.kiasParamsDTOToKias(kiasRequestDTO.getParams());
        kiasExpectedPayment.setPublicId(kiasRequestDTO.getExpectedPaymentId());
        
        //System.out.println(ESBController.asJsonString(kiasRootDTO));
        //System.out.println(ESBController.asJsonString(kiasExpectedPayment));
        
        return kiasExpectedPayment;
    }


    public KiasProcessingStrategy getStrategyFor(String eventCode) {
        log.info("getStrategyFor");
        
        switch(eventCode) {
        case("ExpectedPayCreate") :
            return new KiasExpectedPayCreateStrategy(); 
        default:
            System.out.println("Brak metody");
            return null;
        }
    }


    public ESBResponseRootDTO process(ESBHeader esbHeader, KiasExpectedPayment kiasExpectedPayment, KiasProcessingStrategy processingStrategy) {
        log.info("process");
        
        processingStrategy.setKiasRepository(kiasRepository);

        Optional<KiasExpectedPayment> optional = kiasRepository.findById(kiasExpectedPayment.getPublicId());
        if(optional.isPresent()) {
            kiasExpectedPayment = optional.get();
        } 
        kiasExpectedPayment.addEsbHeader(esbHeader);
        
        return processingStrategy.process(kiasExpectedPayment);
    }
}
