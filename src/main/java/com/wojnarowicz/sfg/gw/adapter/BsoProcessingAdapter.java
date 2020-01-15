package com.wojnarowicz.sfg.gw.adapter;

import com.wojnarowicz.sfg.gw.api.model.bso.BsoResponseRootDTO;
import com.wojnarowicz.sfg.gw.api.model.bso.BsoRootDTO;
import com.wojnarowicz.sfg.gw.repository.AgentRepository;
import com.wojnarowicz.sfg.gw.repository.BsoIssuanceRepository;
import com.wojnarowicz.sfg.gw.repository.BsoRepository;
import com.wojnarowicz.sfg.gw.repository.ContractRepository;

public class BsoProcessingAdapter {

    private AgentRepository agentRepository;
    private BsoRepository bsoRepository;
    private BsoIssuanceRepository bsoIssuanceRepository;
    private ContractRepository contractRepository;
    
    public BsoProcessingAdapter(AgentRepository agentRepository, BsoRepository bsoRepository) {
        this.agentRepository = agentRepository;
        this.bsoRepository = bsoRepository;
    }

    public BsoProcessingAdapter(AgentRepository agentRepository, 
                                BsoRepository bsoRepository, 
                                BsoIssuanceRepository bsoIssuanceRepository, 
                                ContractRepository contractRepository) {
        this.agentRepository = agentRepository;
        this.bsoRepository = bsoRepository;
        this.bsoIssuanceRepository = bsoIssuanceRepository;
        this.contractRepository = contractRepository;
    }

    public BsoResponseRootDTO process(BsoRootDTO bsoRootDTO, BsoProcessingStrategy processingStrategy) {
        processingStrategy.setAgentRepository(agentRepository);
        processingStrategy.setBsoRepository(bsoRepository);
        processingStrategy.setBsoIssuanceRepository(bsoIssuanceRepository);
        processingStrategy.setContractRepository(contractRepository);
        
        return processingStrategy.process(bsoRootDTO);
    }
}
