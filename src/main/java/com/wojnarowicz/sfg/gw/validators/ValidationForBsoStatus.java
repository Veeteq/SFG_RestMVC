package com.wojnarowicz.sfg.gw.validators;

import java.time.LocalDateTime;
import java.util.Optional;

import com.wojnarowicz.sfg.gw.domain.Agent;
import com.wojnarowicz.sfg.gw.domain.BsoDocument;
import com.wojnarowicz.sfg.gw.domain.BsoStatus;

public class ValidationForBsoStatus implements ValidationRule {

    @Override
    public void validate(Optional<Agent> agent, Optional<BsoDocument> bsoDocument, BsoStatus bsoStatus, LocalDateTime issueDate) {
        BsoDocument bso = bsoDocument.get();
        BsoStatus currentStatus = bso.getStatus();
        
        ValidationResult result = currentStatus.checkIfValid(bsoStatus);
        if(!result.isValid()) {
            throw new IllegalArgumentException(result.getMesssage());
        }
    }
}
