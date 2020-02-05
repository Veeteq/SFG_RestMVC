package com.wojnarowicz.sfg.gw.adapter;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
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
import com.wojnarowicz.sfg.gw.api.model.sap.ActOfPerformanceDTO;
import com.wojnarowicz.sfg.gw.config.ExternalProperties;
import com.wojnarowicz.sfg.gw.domain.BCExpectedPayment;
import com.wojnarowicz.sfg.gw.domain.KiasExpectedPayment;
import com.wojnarowicz.sfg.gw.mapper.BCDataApiMapper;
import com.wojnarowicz.sfg.gw.validators.ValidationResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BCDataApiAdapter {
    
    private static final String BC_URL = "http://localhost:8580/bc/rest/";
    private static final String PAYMENT_NOTIFICATION_API = "paymentnotification";
    private static final String ACT_OF_PERFORMANCE_API = "sapactofperformance";
    private static final ObjectMapper _mapper = initMapper();

    private static ObjectMapper initMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        mapper.setSerializationInclusion(Include.NON_EMPTY);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    private final ExternalProperties externalProperties;

    @Autowired
    public BCDataApiAdapter(ExternalProperties externalProperties) {
        this.externalProperties = externalProperties;
    }

    public ESBResponseRootDTO matchPayment(KiasExpectedPayment payment) {
        KiasRootDTO request = new BCDataApiMapper().mapMatchPaymentRequest(payment);
        
        String url = BC_URL + PAYMENT_NOTIFICATION_API;
        String eventCode = "ActualPaymentCreate";

        String requestJson = null;
        try {
            requestJson = _mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);
            log.info(requestJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return getBcDataApiResponse(url, eventCode, requestJson);        
    }

    public ESBResponseRootDTO actOfPerformance(List<? extends BCExpectedPayment> payments) {
        ActOfPerformanceDTO request = new BCDataApiMapper().mapActOfPerformance(payments);
        
        String url = BC_URL + ACT_OF_PERFORMANCE_API;
        String eventCode = "ActOfPerformanceCreate";
        
        String requestJson = null;
        try {
            requestJson = _mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);
            log.info(requestJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
        return getBcDataApiResponse(url, eventCode, requestJson);
    }

    private ESBResponseRootDTO getBcDataApiResponse(String url, String eventCode, String requestJson) {
        try {
            ESBResponseRootDTO response = sendBcDataApiRequest(url, eventCode, requestJson);

            String responseJson = _mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
            log.info(responseJson);
            
            return response;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ESBResponseRootDTO sendBcDataApiRequest(String url, String eventCode, String requestJson) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(0,new StringHttpMessageConverter(StandardCharsets.UTF_8));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        String authStr = externalProperties.getAuthStr();
        String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());
        headers.add("Authorization", "Basic " + base64Creds);

        headers.add(HeaderConstants.JMSCorrelationID.name(), UUID.randomUUID().toString());
        headers.add(HeaderConstants.EventCode.name(), eventCode);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);

        ResponseEntity<ESBResponseRootDTO> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ESBResponseRootDTO.class);

        switch(responseEntity.getStatusCode()) {
        default:
            return responseEntity.getBody();
        }
    }

    public ValidationResult checkIfValid(ESBResponseRootDTO response) {
        if(response.getNotification().getSummary().getStatus().equalsIgnoreCase("SUCCESS")) {
            return ValidationResult.ok();
        }
        return ValidationResult.fail("Validation failed");
    }
}
