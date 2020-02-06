package com.wojnarowicz.sfg.gw.service;

import java.util.List;
import java.util.Map;

import com.wojnarowicz.sfg.gw.api.model.ESBResponseRootDTO;
import com.wojnarowicz.sfg.gw.api.model.esb.ESBRootDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasRootDTO;
import com.wojnarowicz.sfg.gw.api.model.sap.SapRootDTO;
import com.wojnarowicz.sfg.gw.domain.BCExpectedPayment;
import com.wojnarowicz.sfg.gw.domain.KiasExpectedPayment;

public interface ESBService {

    ESBResponseRootDTO processKiasRequest(Map<String, String> headerMap, KiasRootDTO kiasRootDTO);

    ESBResponseRootDTO processEsbBcRequest(String eventCode, SapRootDTO sapRootDTO);

    ESBResponseRootDTO processEsbPcRequest(String eventCode, ESBRootDTO esbRootDTO);

    void processKiasMatchPayment(KiasExpectedPayment expectedPayment);

    void processActOfPerformance(List<? extends BCExpectedPayment> payments);
}
