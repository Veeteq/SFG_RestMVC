package com.wojnarowicz.sfg.gw.service;

import java.util.List;

import com.wojnarowicz.sfg.gw.api.model.bso.BsoDocumentDTO;
import com.wojnarowicz.sfg.gw.api.model.bso.BsoResponseRootDTO;
import com.wojnarowicz.sfg.gw.api.model.bso.BsoRootDTO;
import com.wojnarowicz.sfg.gw.domain.Agent;
import com.wojnarowicz.sfg.gw.domain.BsoDocument;

public interface BSOService {

    List<BsoDocument> findAll();
    
    BsoDocument save(BsoDocumentDTO bsoDocumentDTO);

    void addBsoToAgent(BsoDocumentDTO bsoDocumentDTO, Agent agent);

    BsoResponseRootDTO processCheckRequest(BsoRootDTO bsoRootDTO);

    BsoResponseRootDTO processUpdateRequest(BsoRootDTO bsoRootDTO);
}
