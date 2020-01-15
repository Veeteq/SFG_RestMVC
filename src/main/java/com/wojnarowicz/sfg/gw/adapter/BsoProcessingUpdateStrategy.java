package com.wojnarowicz.sfg.gw.adapter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.wojnarowicz.sfg.gw.api.builder.ContractBuilder;
import com.wojnarowicz.sfg.gw.api.model.bso.AgentDTO;
import com.wojnarowicz.sfg.gw.api.model.bso.BsoDocumentDTO;
import com.wojnarowicz.sfg.gw.api.model.bso.BsoResponseRootDTO;
import com.wojnarowicz.sfg.gw.api.model.bso.BsoRootDTO;
import com.wojnarowicz.sfg.gw.api.model.bso.ContractDTO;
import com.wojnarowicz.sfg.gw.domain.Agent;
import com.wojnarowicz.sfg.gw.domain.BsoDocument;
import com.wojnarowicz.sfg.gw.domain.BsoStatus;
import com.wojnarowicz.sfg.gw.domain.Contract;
import com.wojnarowicz.sfg.gw.utils.DateUtils;
import com.wojnarowicz.sfg.gw.validators.BsoRequestValidator;

public class BsoProcessingUpdateStrategy extends BsoProcessingAbstractStrategy {

    @Override
    public BsoResponseRootDTO process(BsoRootDTO request) {
        BsoResponseRootDTO response = new BsoResponseRootDTO();
        
        actionId = UUID.fromString(request.getTechData().getActionId());
        correlationId = UUID.fromString(request.getTechData().getCorrelationId());
        
        AgentDTO agentDTO = request.getBusinessData().getAgent();
        BsoDocumentDTO bsoDTO = request.getBusinessData().getBsoDocument();
        ContractDTO contractDTO = request.getBusinessData().getContract();
        
        Optional<Agent> agent = agentRepository.findByLnrAndSkk(agentDTO.getLnr(), agentDTO.getSkk());
        List<BsoDocument> bsoList = bsoRepository.findBySeriesAndNumberAndType(bsoDTO.getSeries(), bsoDTO.getNumber(), bsoDTO.getType());
        BsoStatus newStatus = BsoStatus.getByCode(Integer.parseInt(bsoDTO.getStatus()));
        
        LocalDateTime checkDate = DateUtils.parse(request.getBusinessData().getIssueDate());
                
        try {
            BsoRequestValidator.validateRequest(agent, bsoList, newStatus, checkDate);
            
            Contract contract = null;
            LocalDateTime issueDate = LocalDateTime.now();
            BsoDocument bso = bsoList.get(0);
            
            bso.setStatus(newStatus);
            bso.setUpdateDate(issueDate);
            
            if(newStatus == BsoStatus.USED) {
                contract = contractRepository.findBySeriesAndNumber(contractDTO.getSeries(), contractDTO.getNumber());
                if(contract == null) {
                    contract = ContractBuilder
                            .builder()
                            .withSeries(contractDTO.getSeries())
                            .withNumber(contractDTO.getNumber())
                            .build();
                }
                bso.setContract(contract);
            } else {
                bso.setContract(null);
            }            
            
            BsoDocument savedBso = bsoRepository.save(bso);
            
        } catch (RuntimeException exc) {
            return generateErrorResponse(response, exc.getMessage());
        }
        
        return generateSuccessResponse(response);
    }

}
