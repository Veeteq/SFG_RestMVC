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
import com.wojnarowicz.sfg.gw.api.model.ESBResponseRootDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasRootDTO;
import com.wojnarowicz.sfg.gw.api.model.sap.SapRequestDTO;
import com.wojnarowicz.sfg.gw.builder.ESBResponseBuilder;
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
    
    /*
     * private final static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
     * 
     * private final static String ERROR = "ERROR"; private final static String
     * EVENT_CODE_HEADER = "EventCode"; private final static String
     * CORRELATION_ID_HEADER = "JMSCorrelationID";
     */

    private final ESBService esbService;
    
    @Autowired
    public ESBController(ESBService esbService) {
        this.esbService = esbService;
    }

    @PostMapping(consumes="application/json", produces="application/json", headers = "recipient=KIAS")
    @ResponseStatus(code = HttpStatus.OK)
    public ESBResponseRootDTO processKIASRequest(@RequestHeader Map<String, String> headerMap, @RequestBody KiasRootDTO kiasRootDTO) {
        log.info("processKIASRequest");
        
        headerMap.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, value));
        });
        
        ESBResponseRootDTO response = esbService.processKiasRequest(headerMap, kiasRootDTO);
        return response;
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

    @PostMapping(consumes="application/json", produces="application/json", headers = "recipient=BLA")
    @ResponseStatus(code = HttpStatus.OK)
    public String processBLARequest(@RequestHeader Map<String, String> headerMap) {
        log.info("processBLARequest");

        headerMap.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, value));
        }); 

return "{\"Notification\": {" + 
		"   \"Summary\":    {" + 
		"      \"Status\": \"SUCCESS\"," + 
		"      \"System\": \"GWBC\"" + 
		"   },\r\n" + 
		"   \"Systems\": {\"System\": [   {" + 
		"      \"Details\": {\"Detail\": [      {" + 
		"         \"Component\": \"KIAS_INPUT_ADAPTER\"," + 
		"         \"DateTime\": \"2020-01-20T16:13:43.940Z\"," + 
		"         \"EntityId\": \"200120201609\"," + 
		"         \"Status\": \"SUCCESS\"" + 
		"      }]}," + 
		"      \"GeneralStatus\": \"SUCCESS\"," + 
		"      \"SysName\": \"GWBC\"" + 
		"   }]}" + 
		"}}";
 
         
    }
    
    public static String asJsonString(Object object) {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
