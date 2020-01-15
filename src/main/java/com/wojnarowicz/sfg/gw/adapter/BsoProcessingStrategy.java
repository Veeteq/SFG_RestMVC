package com.wojnarowicz.sfg.gw.adapter;

import com.wojnarowicz.sfg.gw.api.model.bso.BsoResponseRootDTO;
import com.wojnarowicz.sfg.gw.api.model.bso.BsoRootDTO;
import com.wojnarowicz.sfg.gw.repository.AgentRepository;
import com.wojnarowicz.sfg.gw.repository.BsoIssuanceRepository;
import com.wojnarowicz.sfg.gw.repository.BsoRepository;
import com.wojnarowicz.sfg.gw.repository.ContractRepository;

public interface BsoProcessingStrategy {
    public void setAgentRepository(AgentRepository agentRepository);
    public void setBsoRepository(BsoRepository bsoRepository);
    public void setBsoIssuanceRepository(BsoIssuanceRepository bsoIssuanceRepository);
    public void setContractRepository(ContractRepository contractRepository);
    
    public BsoResponseRootDTO process(BsoRootDTO bsoRootDTO);
}
