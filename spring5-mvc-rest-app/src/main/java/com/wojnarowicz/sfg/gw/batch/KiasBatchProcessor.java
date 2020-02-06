package com.wojnarowicz.sfg.gw.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.wojnarowicz.sfg.gw.domain.KiasExpectedPayment;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KiasBatchProcessor implements ItemProcessor<KiasExpectedPayment, KiasExpectedPayment> {

    @Override
    public KiasExpectedPayment process(KiasExpectedPayment kiasExpectedPayment) throws Exception {
        log.info("process: " + kiasExpectedPayment.getPublicId() + ", " + kiasExpectedPayment.getExpectedPaymentNum());
        
        return kiasExpectedPayment;
    }
}
