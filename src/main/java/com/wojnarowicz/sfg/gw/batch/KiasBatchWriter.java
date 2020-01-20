package com.wojnarowicz.sfg.gw.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.wojnarowicz.sfg.gw.api.controller.ESBController;
import com.wojnarowicz.sfg.gw.api.model.ESBResponseRootDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasRootDTO;
import com.wojnarowicz.sfg.gw.service.ESBService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KiasBatchWriter implements ItemWriter<KiasRootDTO> {

	private static final String BC_URL = "http://localhost:8080/api/gw/adapter-bc";

	@Autowired
	private ESBService esbService;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	
	
    @Override
    public void write(List<? extends KiasRootDTO> payments) throws Exception {
        payments.forEach(payment -> {
            log.info(ESBController.asJsonString(payment));
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("recipient", "BLA");
            
            HttpEntity<String> requestEntity = new HttpEntity<>(ESBController.asJsonString(payment), headers); 
            
            ResponseEntity<ESBResponseRootDTO> responseEntity = restTemplate.exchange(BC_URL, HttpMethod.POST, requestEntity, ESBResponseRootDTO.class);
            
            HttpStatus statusCode = responseEntity.getStatusCode();
            log.info("code is: " + statusCode);
            
            ESBResponseRootDTO body = responseEntity.getBody();
            log.info(ESBController.asJsonString(body));
            
            if(body.getNotification().getSummary().getStatus().equals("SUCCESS")) {
            	String expectedPaymentId = payment.getData().getRequest().getParams().getMatchPaymentAccount().getExpPaymentId();            	            
            	log.info(expectedPaymentId);
            	
            	esbService.processKiasMatchPayment(expectedPaymentId);
            }
        });
    }
}
