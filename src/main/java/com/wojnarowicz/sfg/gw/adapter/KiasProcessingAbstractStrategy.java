package com.wojnarowicz.sfg.gw.adapter;

import com.wojnarowicz.sfg.gw.api.model.ESBResponseRootDTO;
import com.wojnarowicz.sfg.gw.builder.ESBResponseBuilder;
import com.wojnarowicz.sfg.gw.repository.KiasRepository;

public abstract class KiasProcessingAbstractStrategy implements KiasProcessingStrategy {

    protected KiasRepository kiasRepository;
    
    @Override
    public void setKiasRepository(KiasRepository kiasRepository) {
        this.kiasRepository = kiasRepository;
    }
    
    protected ESBResponseRootDTO generateSuccessResponse() {
        ESBResponseRootDTO response = ESBResponseBuilder
        .builder()
        .withSummary("SUCCESS", "KIAS")
        .withSystem("SUCCESS", "KIAS")
        .build();
        
        return response;
    }

}
