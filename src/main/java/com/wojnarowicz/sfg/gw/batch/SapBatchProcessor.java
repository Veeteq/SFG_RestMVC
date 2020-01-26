package com.wojnarowicz.sfg.gw.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.wojnarowicz.sfg.gw.domain.BCExpectedPayment;

import lombok.extern.slf4j.Slf4j;

@Component
@Qualifier(value = "sapItemProcessor")
@Slf4j
public class SapBatchProcessor implements ItemProcessor<BCExpectedPayment, BCExpectedPayment> {

    @Override
    public BCExpectedPayment process(BCExpectedPayment bcExpectedPayment) throws Exception {
        log.info("process: " + bcExpectedPayment.getPublicId() + ", " + bcExpectedPayment.getStatementNumber());
        
        return bcExpectedPayment;
    }
}
