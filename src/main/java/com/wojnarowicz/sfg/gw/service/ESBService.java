package com.wojnarowicz.sfg.gw.service;

import java.util.Map;

import com.wojnarowicz.sfg.gw.api.model.ESBResponseRootDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasRootDTO;
import com.wojnarowicz.sfg.gw.api.model.sap.SapRequestDTO;

public interface ESBService {

    ESBResponseRootDTO processKiasRequest(Map<String, String> headerMap, KiasRootDTO kiasRootDTO);

    ESBResponseRootDTO processSapRequest(SapRequestDTO sapRequestData);

	void processKiasMatchPayment(String expectedPaymentId);
}
