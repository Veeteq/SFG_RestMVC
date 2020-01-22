package com.wojnarowicz.sfg.gw.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojnarowicz.sfg.gw.adapter.KiasProcessingAdapter;
import com.wojnarowicz.sfg.gw.adapter.KiasProcessingStrategy;
import com.wojnarowicz.sfg.gw.api.model.ESBResponseRootDTO;
import com.wojnarowicz.sfg.gw.api.model.esb.ESBRootDTO;
import com.wojnarowicz.sfg.gw.api.model.esb.PCPolicyDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasRootDTO;
import com.wojnarowicz.sfg.gw.api.model.sap.SapRequestDTO;
import com.wojnarowicz.sfg.gw.builder.ESBResponseBuilder;
import com.wojnarowicz.sfg.gw.domain.ESBHeader;
import com.wojnarowicz.sfg.gw.domain.GWKiasPaymentStatus;
import com.wojnarowicz.sfg.gw.domain.KiasExpectedPayment;
import com.wojnarowicz.sfg.gw.domain.PCPolicy;
import com.wojnarowicz.sfg.gw.mapper.PCPolicyMapper;
import com.wojnarowicz.sfg.gw.repository.KiasRepository;
import com.wojnarowicz.sfg.gw.repository.PCPolicyRepository;
import com.wojnarowicz.sfg.gw.service.ESBService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ESBServiceImpl implements ESBService {

    private final KiasRepository kiasRepository;
    private final PCPolicyRepository pcPolicyRepository;
    
    @Autowired
    public ESBServiceImpl(KiasRepository kiasRepository, PCPolicyRepository pcPolicyRepository) {
        this.kiasRepository = kiasRepository;
        this.pcPolicyRepository = pcPolicyRepository;
    }

    @Override
    public ESBResponseRootDTO processSapRequest(SapRequestDTO sapRequestDTO) {
        return null;
    }

    @Override
    public ESBResponseRootDTO processKiasRequest(Map<String, String> headerMap, KiasRootDTO kiasRootDTO) {
        log.info("processKiasRequest");

        KiasProcessingAdapter processingAdapter = new KiasProcessingAdapter(kiasRepository);
        ESBHeader esbHeader = processingAdapter.processHeader(headerMap);
        
        KiasExpectedPayment kiasExpectedPayment = processingAdapter.processBody(kiasRootDTO);

        KiasProcessingStrategy strategy = processingAdapter.getStrategyFor(esbHeader.getEventCode());
        ESBResponseRootDTO response = processingAdapter.process(esbHeader, kiasExpectedPayment, strategy);
        
        return response;
    }

	@Override
	public void processKiasMatchPayment(String expectedPaymentId) {
		log.info("processKiasMatchPayment");
		
		KiasExpectedPayment expectedPayment = kiasRepository.findById(expectedPaymentId).orElse(null);
		expectedPayment.setPaymentStatus(GWKiasPaymentStatus.MATCHED);
		kiasRepository.save(expectedPayment);
	}

	@Override
	public ESBResponseRootDTO processPCRequest(String eventCode, ESBRootDTO esbRootDTO) {
	    log.info("processPCRequest");

	    PCPolicyDTO policyDTO = esbRootDTO.getPolicyDTO();
	    PCPolicy pcPolicy = PCPolicyMapper.INSTANCE.pcPolicyDTOToPCPolicy(policyDTO);

	    log.info(pcPolicy.getOwner().getFirstName());
	    //log.info(pcPolicy.getInsured().getFirstName());

	    PCPolicy savedPolicy = pcPolicyRepository.save(pcPolicy);
	    log.info(savedPolicy.getPublicId() + ", " + savedPolicy.getPolicyNumber());

	    ESBResponseRootDTO response = ESBResponseBuilder
	            .builder()
	            .withSummary("SUCCESS", "ESB")
	            .withSystem("SUCCESS", "ESB")
	            .withDetails(savedPolicy.getPublicId(), null, "GW_INPUT_ADAPTER", "SUCCESS")
	            .withExtendedDetails("ESB-66001", null, "ИП начала обработку запроса")
	            .build();

	    return response;
	}
}
