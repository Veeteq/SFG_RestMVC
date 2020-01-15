package com.wojnarowicz.sfg.gw.adapter;

import java.util.ArrayList;
import java.util.List;

import com.wojnarowicz.sfg.gw.api.mapper.KiasRequestMapper;
import com.wojnarowicz.sfg.gw.api.mapper.KiasRequestMapperImpl;
import com.wojnarowicz.sfg.gw.api.model.ResponseNotificationDTO;
import com.wojnarowicz.sfg.gw.api.model.ResponseRootDTO;
import com.wojnarowicz.sfg.gw.api.model.ResponseSummaryDTO;
import com.wojnarowicz.sfg.gw.api.model.ResponseSystemDTO;
import com.wojnarowicz.sfg.gw.api.model.ResponseSystemsDTO;
import com.wojnarowicz.sfg.gw.api.model.kias.KiasRequestDTO;
import com.wojnarowicz.sfg.gw.domain.KiasRequestData;

public class KiasProcessingAdapter {

    private final String eventCode;
    
    public KiasProcessingAdapter(String eventCode) {
        this.eventCode = eventCode;
    }

    
    public KiasRequestData process(KiasRequestDTO requestData) {
        if(eventCode.equals("ExpectedPayCreate")) {
            KiasRequestMapper kiasRequestMapper = new KiasRequestMapperImpl(); 
            
            return kiasRequestMapper.requestDataDTOToRequestData(requestData);            
        }
        return null;
    }

    
    public ResponseRootDTO getResponse() {
        ResponseRootDTO response = new ResponseRootDTO();
        
        ResponseNotificationDTO notification = new ResponseNotificationDTO();
        
        ResponseSummaryDTO summary = new ResponseSummaryDTO();
        summary.setSystem("KIAS");
        summary.setStatus("SUCCESS");
        notification.setSummary(summary);
        
        ResponseSystemsDTO systems = new ResponseSystemsDTO();
        List<ResponseSystemDTO> systemsList = new ArrayList<ResponseSystemDTO>();
        
        ResponseSystemDTO system = new ResponseSystemDTO();
        system.setGeneralStatus("SUCCESS");
        system.setSysName("KIAS");
        systemsList.add(system );
        systems.setSystems(systemsList);
        notification.setSystems(systems);
        
        response.setNotification(notification);
        
        return response;
    }
}
