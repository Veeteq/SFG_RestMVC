package com.wojnarowicz.sfg.gw.service;

import java.util.Map;

import com.wojnarowicz.sfg.gw.api.model.ResponseRootDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasRequestDTO;
import com.wojnarowicz.sfg.gw.api.model.sap.SapRequestDTO;

public interface ESBService {

    ResponseRootDTO process(Map<String, String> headerMap, KiasRequestDTO requestData);

    void processSapRequest(SapRequestDTO sapRequestData);

}
