package com.wojnarowicz.sfg.gw.adapter;

import com.wojnarowicz.sfg.gw.api.model.ESBResponseRootDTO;
import com.wojnarowicz.sfg.gw.domain.KiasExpectedPayment;
import com.wojnarowicz.sfg.gw.repository.KiasRepository;

public interface KiasProcessingStrategy {
    public void setKiasRepository(KiasRepository kiasRepository);
    
    public ESBResponseRootDTO process(KiasExpectedPayment kiasExpectedPayment);
}
