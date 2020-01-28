package com.wojnarowicz.sfg.gw.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wojnarowicz.sfg.gw.adapter.BCDataApiAdapter;
import com.wojnarowicz.sfg.gw.api.model.ESBResponseRootDTO;
import com.wojnarowicz.sfg.gw.domain.BCExpectedPayment;
import com.wojnarowicz.sfg.gw.service.ESBService;
import com.wojnarowicz.sfg.gw.validators.ValidationResult;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SapBatchWriter implements ItemWriter<BCExpectedPayment> {

    private final BCDataApiAdapter bcDataApiAdapter = new BCDataApiAdapter();

	@Autowired
	private ESBService esbService;
	
    @Override
    public void write(List<? extends BCExpectedPayment> payments) throws Exception {
            log.info("creating payload for ActOfPerformance");
            
            ESBResponseRootDTO response = bcDataApiAdapter.actOfPerformance(payments);
            
            ValidationResult result = bcDataApiAdapter.checkIfValid(response);
            
            if(result.isValid()) {
                esbService.processActOfPerformance(payments);
            }            
    }
}
