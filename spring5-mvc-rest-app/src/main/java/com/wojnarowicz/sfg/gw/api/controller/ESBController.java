package com.wojnarowicz.sfg.gw.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wojnarowicz.sfg.gw.api.model.ESBResponseRootDTO;
import com.wojnarowicz.sfg.gw.api.model.esb.ESBRootDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasRootDTO;
import com.wojnarowicz.sfg.gw.api.model.sap.SapRootDTO;
import com.wojnarowicz.sfg.gw.service.ESBService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = ESBController.BASE_URL)
public class ESBController {

    public static final String  BASE_URL = "/api/gw";
    private final static ObjectMapper _mapper = initMapper();
        
    private final ESBService esbService;
    
    @Autowired
    public ESBController(ESBService esbService) {
        this.esbService = esbService;
    }

    private static ObjectMapper initMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        mapper.setSerializationInclusion(Include.NON_EMPTY);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    @PostMapping(path="/adapter-bc", consumes="application/json", produces="application/json", headers = "recipient=KIAS")
    @ResponseStatus(code = HttpStatus.OK)
    public ESBResponseRootDTO processKIASRequest(@RequestHeader Map<String, String> headerMap, @RequestBody KiasRootDTO kiasRootDTO) throws JsonProcessingException {
        log.info("processKIASRequest");
        
        headerMap.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, value));
        });
        
        log.info(_mapper.writerWithDefaultPrettyPrinter().writeValueAsString(kiasRootDTO));
        
        ESBResponseRootDTO response = esbService.processKiasRequest(headerMap, kiasRootDTO);
        return response;
    }

    @PostMapping(path="/adapter-bc",consumes="application/json", produces="application/json", headers = "recipient=ESB")
    @ResponseStatus(code = HttpStatus.OK)
    public ESBResponseRootDTO processEsbBcRequest(@RequestHeader Map<String, String> headerMap, @RequestBody SapRootDTO sapRootDTO) throws JsonProcessingException {
        log.info("process ESB BC Request");
        log.info(_mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sapRootDTO));

        headerMap.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, value));
        }); 

        String eventCode = headerMap.get(HeaderConstants.EventCode.toString().toLowerCase());
        
        ESBResponseRootDTO response = esbService.processEsbBcRequest(eventCode, sapRootDTO);

        log.info(_mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
        return response;
    }

    @PostMapping(path="/adapter-pc",consumes="application/json", produces="application/json", headers = "recipient=ESB")
    @ResponseStatus(code = HttpStatus.OK)
    public ESBResponseRootDTO processEsbPcRequest(@RequestHeader Map<String, String> headerMap, @RequestBody ESBRootDTO esbRootDTO) throws JsonProcessingException {
        log.info("process ESB PC Request");
        log.info(_mapper.writerWithDefaultPrettyPrinter().writeValueAsString(esbRootDTO));

        headerMap.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, value));
        });

        //String uuid = headerMap.get(HeaderConstants.JMSCorrelationID.toString().toLowerCase());
        String eventCode = headerMap.get(HeaderConstants.EventCode.toString().toLowerCase());
        
        ESBResponseRootDTO response = esbService.processEsbPcRequest(eventCode, esbRootDTO);
        
        log.info(_mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
        return response;
    }
}
