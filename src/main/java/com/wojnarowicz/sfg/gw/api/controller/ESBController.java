package com.wojnarowicz.sfg.gw.api.controller;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wojnarowicz.sfg.gw.api.builder.ESBResponseBuilder;
import com.wojnarowicz.sfg.gw.api.model.ResponseRootDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasRequestDTO;
import com.wojnarowicz.sfg.gw.api.model.sap.ESBResponseRootDTO;
import com.wojnarowicz.sfg.gw.api.model.sap.SapRequestDTO;
import com.wojnarowicz.sfg.gw.service.ESBService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = ESBController.BASE_URL)
public class ESBController {

    public static final String  BASE_URL = "/api/gw/adapter-bc";
    
    private final static String SYSTEM = "ESB";
    private final static String SUCCESS = "SUCCESS";
    private final static String COMPONENT = "GW_INPUT_ADAPTER";
    
    private final static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    
    private final static String ERROR = "ERROR";
    private final static String EVENT_CODE_HEADER = "EventCode";
    private final static String CORRELATION_ID_HEADER = "JMSCorrelationID";

    private final ESBService esbService;
    
    @Autowired
    public ESBController(ESBService esbService) {
        this.esbService = esbService;
    }

    @PostMapping(consumes="application/json", produces="application/json", headers = "recipient=KIAS")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseRootDTO processKIASRequest(@RequestHeader Map<String, String> headerMap, @RequestBody KiasRequestDTO requestData) {
        log.info("processKIASRequest");
        
        headerMap.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, value));
        });
        
        String eventCode = headerMap.get(HeaderConstants.EventCode.name().toLowerCase());
        String recipient = headerMap.get(HeaderConstants.Recipient.name().toLowerCase());
        String correlationID = headerMap.get(HeaderConstants.JMSCorrelationID.name());
        
        //return esbService.process(headerMap, requestData);
        return new ResponseRootDTO();
    }

    @PostMapping(consumes="application/json", produces="application/json", headers = "recipient=ESB")
    @ResponseStatus(code = HttpStatus.OK)
    public ESBResponseRootDTO processSAPRequest(@RequestHeader Map<String, String> headerMap, @RequestBody SapRequestDTO sapRequestData) {
        log.info("processSAPRequest");

        String correlationID = headerMap.get(HeaderConstants.JMSCorrelationID.name());

        headerMap.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, value));
        }); 

        log.info(asJsonString(sapRequestData));

        esbService.processSapRequest(sapRequestData);

        String currDate = DateTimeFormatter.ISO_INSTANT.format(Instant.now());
        
        ESBResponseRootDTO response = ESBResponseBuilder.builder()
                .withSummary(SUCCESS, SYSTEM)
                .withSystem(SUCCESS, SYSTEM)
                .withDetails(correlationID, currDate, COMPONENT, SUCCESS)     
                .withExtendedDetails("ESB-001", SUCCESS, "ИП начала обработку запроса")
                .build();
        
        log.info(asJsonString(response));
        return response;
    }

    private String asJsonString(Object object) {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
