package com.wojnarowicz.sfg.gw.adapter;

import com.wojnarowicz.sfg.gw.api.model.ESBResponseRootDTO;
import com.wojnarowicz.sfg.gw.domain.KiasExpectedPayment;

public class KiasExpectedPayCreateStrategy extends KiasProcessingAbstractStrategy {

    @Override
    public ESBResponseRootDTO process(KiasExpectedPayment kiasExpectedPayment) {
        
        kiasRepository.save(kiasExpectedPayment);
        
        return generateSuccessResponse();
    }
}
