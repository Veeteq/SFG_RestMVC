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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wojnarowicz.sfg.gw.api.model.bso.BsoResponseRootDTO;
import com.wojnarowicz.sfg.gw.api.model.bso.BsoRootDTO;
import com.wojnarowicz.sfg.gw.service.BSOService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = BSOController.BASE_URL)
public class BSOController {

    public static final String  BASE_URL = "/api/gw";
    private final BSOService bsoService;
    
    @Autowired
    public BSOController(BSOService bsoService) {
       this.bsoService = bsoService;
    }

    @PostMapping(path = "/check_bso5", consumes="application/json", produces="application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public BsoResponseRootDTO check_bso5(@RequestHeader Map<String, String> headerMap, @RequestBody BsoRootDTO bsoRootDTO) {
        log.info("check_bso5");
        
        headerMap.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, value));
        });
        
        log.info(asJsonString(bsoRootDTO));
        return null;
    }

    @PostMapping(path = "/status_bso", consumes="application/json", produces="application/json")
    @ResponseStatus(code = HttpStatus.OK)
    public BsoResponseRootDTO status_bso(@RequestHeader Map<String, String> headerMap, @RequestBody BsoRootDTO bsoRootDTO) {
        log.info("status_bso");
        
        headerMap.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, value));
        }); 
                
        log.info(asJsonString(bsoRootDTO));
        
        BsoResponseRootDTO response = bsoService.processUpdateRequest(bsoRootDTO);        
        
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
