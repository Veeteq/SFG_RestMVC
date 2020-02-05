package com.wojnarowicz.sfg.gw.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojnarowicz.sfg.gw.adapter.KiasProcessingAdapter;
import com.wojnarowicz.sfg.gw.api.mapper.BCExpectedPaymentMapper;
import com.wojnarowicz.sfg.gw.api.mapper.JpaContext;
import com.wojnarowicz.sfg.gw.api.mapper.PCPolicyMapper;
import com.wojnarowicz.sfg.gw.api.model.ESBResponseRootDTO;
import com.wojnarowicz.sfg.gw.api.model.esb.ESBRootDTO;
import com.wojnarowicz.sfg.gw.api.model.esb.PCPolicyDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasRootDTO;
import com.wojnarowicz.sfg.gw.api.model.sap.SapRootDTO;
import com.wojnarowicz.sfg.gw.builder.ESBResponseBuilder;
import com.wojnarowicz.sfg.gw.domain.Agent;
import com.wojnarowicz.sfg.gw.domain.AgentRole;
import com.wojnarowicz.sfg.gw.domain.BCExpectedPayment;
import com.wojnarowicz.sfg.gw.domain.ESBHeader;
import com.wojnarowicz.sfg.gw.domain.GWKiasPaymentStatus;
import com.wojnarowicz.sfg.gw.domain.KiasExpectedPayment;
import com.wojnarowicz.sfg.gw.domain.PCPolicy;
import com.wojnarowicz.sfg.gw.domain.BCPaymentStatus;
import com.wojnarowicz.sfg.gw.repository.AgentRepository;
import com.wojnarowicz.sfg.gw.repository.BCExpectedPaymentRepository;
import com.wojnarowicz.sfg.gw.repository.KiasRepository;
import com.wojnarowicz.sfg.gw.repository.PCPolicyRepository;
import com.wojnarowicz.sfg.gw.service.ESBService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ESBServiceImpl implements ESBService {

    private final static String SYSTEM = "ESB";
    private final static String SUCCESS = "SUCCESS";
    private final static String COMPONENT = "GW_INPUT_ADAPTER";
   
    private final KiasRepository kiasRepository;
    private final PCPolicyRepository pcPolicyRepository;
    private final BCExpectedPaymentRepository bcExpectedPaymentRepository;
    private final AgentRepository agentRepository;
    
    @Autowired
    public ESBServiceImpl(KiasRepository kiasRepository, PCPolicyRepository pcPolicyRepository, BCExpectedPaymentRepository bcExpectedPaymentRepository, AgentRepository agentRepository) {
        this.kiasRepository = kiasRepository;
        this.pcPolicyRepository = pcPolicyRepository;
        this.bcExpectedPaymentRepository = bcExpectedPaymentRepository;
        this.agentRepository = agentRepository;
    }

    @Override
    public ESBResponseRootDTO processKiasRequest(Map<String, String> headerMap, KiasRootDTO kiasRootDTO) {
        log.info("processKiasRequest");

        KiasProcessingAdapter processingAdapter = new KiasProcessingAdapter(kiasRepository);
        ESBHeader esbHeader = processingAdapter.processHeader(headerMap);

        KiasExpectedPayment kiasExpectedPayment = processingAdapter.processBody(kiasRootDTO);

        Optional<KiasExpectedPayment> optional = kiasRepository.findById(kiasExpectedPayment.getPublicId());
        if(optional.isPresent()) {
            kiasExpectedPayment = optional.get();
        } 
        kiasExpectedPayment.addEsbHeader(esbHeader);
        
        KiasExpectedPayment savedKiasExpectedPayment = kiasRepository.save(kiasExpectedPayment);

        ESBResponseRootDTO response = ESBResponseBuilder
                .builder()
                .withSummary("SUCCESS", "KIAS")
                .withSystem("SUCCESS", "KIAS")
                .withDetails(savedKiasExpectedPayment.getPublicId(), null, COMPONENT, SUCCESS)    
                .build();

        return response;
    }

	@Override
	public void processKiasMatchPayment(KiasExpectedPayment sourceExpectedPayment) {
		log.info("processKiasMatchPayment");
		
		KiasExpectedPayment expectedPayment = kiasRepository.findById(sourceExpectedPayment.getPublicId()).orElse(null);
		expectedPayment.setPaymentStatus(GWKiasPaymentStatus.MATCHED);
		kiasRepository.save(expectedPayment);
	}

    @Override
    public ESBResponseRootDTO processEsbBcRequest(String eventCode, SapRootDTO sapRootDTO) {
        log.info("processEsbBcRequest");
        
        JpaContext ctx = new JpaContext();
        BCExpectedPayment bcExpectedPayment = BCExpectedPaymentMapper.INSTANCE.toBCExpectedPayment(sapRootDTO, ctx);
        bcExpectedPayment.getCoverages().forEach(coverage -> coverage.setExpectedPayment(bcExpectedPayment));
        
        
        sapRootDTO.getAgents().stream().forEach(agent -> {
            agent.getAgentRoles().forEach(role ->{
                Agent savedAgent = agentRepository.findById(Long.valueOf(agent.getAgentCode())).orElse(null);

                bcExpectedPayment.addAgentRole(savedAgent, AgentRole.getByCode(role));
            });
        });
        
        log.info(bcExpectedPayment.getId());
        log.info(bcExpectedPayment.getPolicyId());
        log.info(bcExpectedPayment.getStatementNumber());
        
        bcExpectedPayment.getCoverages().forEach(coverage -> {
            log.info("\t" + coverage.getChargeAmount().toString());
            log.info("\t" + coverage.getChargeCode());
            log.info("\t" + coverage.getId());
            log.info("\t" + coverage.getExpectedPayment().getId());
        });
        
        bcExpectedPayment.getPaymentAgents().forEach(paymentAgent -> {
            log.info("\t" + paymentAgent.getKey());
            log.info("\t" + paymentAgent.getAgent().getName());
            log.info("\t" + paymentAgent.getAgentRole());
        });
        
        BCExpectedPayment savedBcExpectedPayment = bcExpectedPaymentRepository.save(bcExpectedPayment);
        
        ESBResponseRootDTO response = ESBResponseBuilder.builder()
                .withSummary(SUCCESS, SYSTEM)
                .withSystem(SUCCESS, SYSTEM)
                .withDetails(savedBcExpectedPayment.getId(), null, COMPONENT, SUCCESS)     
                .withExtendedDetails("ESB-001", SUCCESS, "ИП начала обработку запроса")
                .build();
        
        return response;
    }


	@Override
	public ESBResponseRootDTO processEsbPcRequest(String eventCode, ESBRootDTO esbRootDTO) {
	    log.info("processPCRequest");

	    PCPolicyDTO policyDTO = esbRootDTO.getPolicyDTO();
	    PCPolicy pcPolicy = PCPolicyMapper.INSTANCE.pcPolicyDTOToPCPolicy(policyDTO);

	    log.info(pcPolicy.getOwner().getFirstName());
	    log.info(pcPolicy.getInsured().getFirstName());
	    log.info(pcPolicy.getMVehicle().getModel());
	    log.info(pcPolicy.getPublicId());
	    
	    
	    PCPolicy savedPolicy = pcPolicyRepository.save(pcPolicy);
	    log.info(savedPolicy.getPublicId() + ", " + savedPolicy.getPolicyNumber());

	    ESBResponseRootDTO response = ESBResponseBuilder
	            .builder()
	            .withSummary(SUCCESS, SYSTEM)
	            .withSystem(SUCCESS, SYSTEM)
	            .withDetails(savedPolicy.getPublicId(), null, "GW_INPUT_ADAPTER", SUCCESS)
	            .withExtendedDetails("ESB-66001", null, "ИП начала обработку запроса")
	            .build();

	    return response;
	}

    @Override
    public void processActOfPerformance(List<? extends BCExpectedPayment> payments) {
        payments.stream().forEach(payment -> {
            Optional<BCExpectedPayment> optional = bcExpectedPaymentRepository.findById(payment.getId());
            if(optional.isPresent()) {
                BCExpectedPayment expectedPayment = optional.get();
                expectedPayment.setPaymentStatus(BCPaymentStatus.getById(1));
                bcExpectedPaymentRepository.save(expectedPayment);
            }
        });
    }
}
