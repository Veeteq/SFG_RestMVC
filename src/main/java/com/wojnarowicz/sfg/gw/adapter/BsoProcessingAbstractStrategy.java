package com.wojnarowicz.sfg.gw.adapter;

import java.util.UUID;

import com.wojnarowicz.sfg.gw.api.model.bso.BsoResponseBusinessData;
import com.wojnarowicz.sfg.gw.api.model.bso.BsoResponseRootDTO;
import com.wojnarowicz.sfg.gw.api.model.bso.BsoResponseTechData;
import com.wojnarowicz.sfg.gw.repository.AgentRepository;
import com.wojnarowicz.sfg.gw.repository.BsoIssuanceRepository;
import com.wojnarowicz.sfg.gw.repository.BsoRepository;
import com.wojnarowicz.sfg.gw.repository.ContractRepository;

public abstract class BsoProcessingAbstractStrategy implements BsoProcessingStrategy {

    protected AgentRepository agentRepository;
    protected BsoRepository bsoRepository;
    protected BsoIssuanceRepository bsoIssuanceRepository; 
    protected ContractRepository contractRepository;
        
    protected UUID actionId;
    protected UUID correlationId;

    public void setAgentRepository(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public void setBsoRepository(BsoRepository bsoRepository) {
        this.bsoRepository = bsoRepository;
    }

    public void setBsoIssuanceRepository(BsoIssuanceRepository bsoIssuanceRepository) {
        this.bsoIssuanceRepository = bsoIssuanceRepository;
    }

    public void setContractRepository(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    protected BsoResponseRootDTO generateErrorResponse(BsoResponseRootDTO response, String errMsg) {
        BsoResponseTechData techData = new BsoResponseTechData();
        techData.setActionId(actionId.toString());
        techData.setCorrelationId(correlationId.toString());
        techData.setResponseCode("0");
        response.setTechData(techData);
        
        BsoResponseBusinessData bussinessData = new BsoResponseBusinessData();
        bussinessData.setResult(errMsg);
        response.setBusinessData(bussinessData);
        
        return response;
    }

    protected BsoResponseRootDTO generateSuccessResponse(BsoResponseRootDTO response) {
        BsoResponseTechData techData = new BsoResponseTechData();
        techData.setActionId(actionId.toString());
        techData.setCorrelationId(correlationId.toString());
        techData.setResponseCode("0");
        response.setTechData(techData);

        BsoResponseBusinessData data = new BsoResponseBusinessData();
        data.setResult("OK");
        data.setSystem("1");
        response.setBusinessData(data);
        
        return response;
    }
}
