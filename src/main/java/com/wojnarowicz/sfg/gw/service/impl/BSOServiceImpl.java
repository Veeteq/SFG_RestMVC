package com.wojnarowicz.sfg.gw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wojnarowicz.sfg.gw.adapter.BsoProcessingAdapter;
import com.wojnarowicz.sfg.gw.adapter.BsoProcessingUpdateStrategy;
import com.wojnarowicz.sfg.gw.api.model.bso.BsoDocumentDTO;
import com.wojnarowicz.sfg.gw.api.model.bso.BsoResponseRootDTO;
import com.wojnarowicz.sfg.gw.api.model.bso.BsoRootDTO;
import com.wojnarowicz.sfg.gw.domain.Agent;
import com.wojnarowicz.sfg.gw.domain.BsoDocument;
import com.wojnarowicz.sfg.gw.repository.AgentRepository;
import com.wojnarowicz.sfg.gw.repository.BsoRepository;
import com.wojnarowicz.sfg.gw.repository.ContractRepository;
import com.wojnarowicz.sfg.gw.service.BSOService;

@Service
public class BSOServiceImpl implements BSOService {

    private final AgentRepository agentRepository;
    private final BsoRepository bsoRepository;
    private final ContractRepository contractRepository;

    @Autowired
    public BSOServiceImpl(AgentRepository agentRepository, 
                      BsoRepository bsoRepository, 
                      ContractRepository contractRepository) {
        this.agentRepository = agentRepository;
        this.bsoRepository = bsoRepository;
        this.contractRepository = contractRepository;
    }
    
    @Override
    public List<BsoDocument> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BsoDocument save(BsoDocumentDTO bsoDocumentDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addBsoToAgent(BsoDocumentDTO bsoDocumentDTO, Agent agent) {
        // TODO Auto-generated method stub

    }

    @Override
    public BsoResponseRootDTO processCheckRequest(BsoRootDTO bsoRootDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BsoResponseRootDTO processUpdateRequest(BsoRootDTO bsoRootDTO) {
        
        BsoProcessingAdapter processingAdapter = new BsoProcessingAdapter(agentRepository, bsoRepository, contractRepository);
        BsoResponseRootDTO response = processingAdapter.process(bsoRootDTO, new BsoProcessingUpdateStrategy());

        return response;
    }

}
