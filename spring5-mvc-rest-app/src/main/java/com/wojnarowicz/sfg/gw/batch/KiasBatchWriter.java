package com.wojnarowicz.sfg.gw.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wojnarowicz.sfg.gw.adapter.BCDataApiAdapter;
import com.wojnarowicz.sfg.gw.api.model.ESBResponseRootDTO;
import com.wojnarowicz.sfg.gw.domain.KiasExpectedPayment;
import com.wojnarowicz.sfg.gw.service.ESBService;
import com.wojnarowicz.sfg.gw.validators.ValidationResult;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KiasBatchWriter implements ItemWriter<KiasExpectedPayment> {

    private final BCDataApiAdapter bcDataApiAdapter;
    private final ESBService esbService;

	@Autowired
    public KiasBatchWriter(BCDataApiAdapter bcDataApiAdapter, ESBService esbService) {
        this.bcDataApiAdapter = bcDataApiAdapter;
        this.esbService = esbService;
    }
	
    @Override
    public void write(List<? extends KiasExpectedPayment> payments) throws Exception {
        payments.forEach(payment -> {
            log.info("write: " + payment.getPublicId() + ", " + payment.getExpectedPaymentNum());
            
            ESBResponseRootDTO response = bcDataApiAdapter.matchPayment(payment);
            
            ValidationResult result = bcDataApiAdapter.checkIfValid(response);
            
            if(result.isValid()) {
                esbService.processKiasMatchPayment(payment);
            }            
        });
    }
}
