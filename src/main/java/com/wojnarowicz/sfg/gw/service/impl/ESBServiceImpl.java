package com.wojnarowicz.sfg.gw.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojnarowicz.sfg.gw.adapter.KiasProcessingAdapter;
import com.wojnarowicz.sfg.gw.adapter.KiasProcessingStrategy;
import com.wojnarowicz.sfg.gw.api.model.ESBResponseRootDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasRootDTO;
import com.wojnarowicz.sfg.gw.api.model.sap.SapRequestDTO;
import com.wojnarowicz.sfg.gw.domain.ESBHeader;
import com.wojnarowicz.sfg.gw.domain.KiasExpectedPayment;
import com.wojnarowicz.sfg.gw.repository.KiasRepository;
import com.wojnarowicz.sfg.gw.service.ESBService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ESBServiceImpl implements ESBService {

    private KiasRepository kiasRepository;
    
    @Autowired
    public ESBServiceImpl(KiasRepository kiasRepository) {
        this.kiasRepository = kiasRepository;
    }

    @Override
    public ESBResponseRootDTO processSapRequest(SapRequestDTO sapRequestData) {
        return null;
    }

    @Override
    public ESBResponseRootDTO processKiasRequest(Map<String, String> headerMap, KiasRootDTO kiasRootDTO) {
        log.info("processKiasRequest");

        KiasProcessingAdapter processingAdapter = new KiasProcessingAdapter(kiasRepository);
        ESBHeader esbHeader = processingAdapter.processHeader(headerMap);
        
        KiasExpectedPayment kiasExpectedPayment = processingAdapter.processBody(kiasRootDTO);

        KiasProcessingStrategy strategy = processingAdapter.getStrategyFor(esbHeader.getEventCode());
        ESBResponseRootDTO response = processingAdapter.process(esbHeader, kiasExpectedPayment, strategy);
        
        return response;
    }
}
