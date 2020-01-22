package com.wojnarowicz.sfg.gw.adapter;

import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wojnarowicz.sfg.gw.api.controller.HeaderConstants;
import com.wojnarowicz.sfg.gw.api.model.ESBResponseRootDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasRootDTO;
import com.wojnarowicz.sfg.gw.domain.KiasExpectedPayment;
import com.wojnarowicz.sfg.gw.mapper.BCDataApiMapper;
import com.wojnarowicz.sfg.gw.validators.ValidationResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BCDataApiAdapter {
    
    private static final String BC_URL = "http://localhost:8580/bc/rest/paymentnotification";
    private final static ObjectMapper _mapper = initMapper();

    private static ObjectMapper initMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        mapper.setSerializationInclusion(Include.NON_EMPTY);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    public ESBResponseRootDTO matchPayment(KiasExpectedPayment payment) {
        KiasRootDTO request = new BCDataApiMapper().mapMatchPaymentRequest(payment);
        
        return getBcDataApiResponse(request);
        
    }

    private ESBResponseRootDTO getBcDataApiResponse(KiasRootDTO request) {
        try {
            String requestJson = _mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);
            log.info(requestJson);
                
            ESBResponseRootDTO response = sendBcDataApiRequest(requestJson);

            String responseJson = _mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
            log.info(responseJson);
            
            return response;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ESBResponseRootDTO sendBcDataApiRequest(String requestJson) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);        
        headers.add(HeaderConstants.JMSCorrelationID.name(), UUID.randomUUID().toString());
        headers.add(HeaderConstants.EventCode.name(), "ActualPaymentCreate");

        HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);

        ResponseEntity<ESBResponseRootDTO> responseEntity = restTemplate.exchange(BC_URL, HttpMethod.POST, requestEntity, ESBResponseRootDTO.class);

        switch(responseEntity.getStatusCode()) {
        default:
            return responseEntity.getBody();
        }
    }

    public ValidationResult checkIfValid(ESBResponseRootDTO response) {
        if(response.getNotification().getSummary().getStatus().equals("SUCCESS")) {
            return ValidationResult.ok();
        }
        return ValidationResult.fail("Validation failed");
    }
}
