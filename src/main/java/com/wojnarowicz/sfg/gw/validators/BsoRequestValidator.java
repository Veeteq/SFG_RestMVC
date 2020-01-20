package com.wojnarowicz.sfg.gw.validators;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.wojnarowicz.sfg.gw.domain.Agent;
import com.wojnarowicz.sfg.gw.domain.BsoDocument;
import com.wojnarowicz.sfg.gw.domain.BsoStatus;

public class BsoRequestValidator {

    public static ValidationResult validateRequest(Optional<Agent> agent, Optional<BsoDocument> bsoDocument, BsoStatus bsoStatus, LocalDateTime checkDate) {
        List<ValidationRule> rules = new ArrayList<>();
        rules.add(new ValidationForAgent());
        rules.add(new ValidationForBso());
        rules.add(new ValidationForAgentAndBso());
        rules.add(new ValidationForCheckDate());
        rules.add(new ValidationForBsoStatus());        
        
        for (ValidationRule rule : rules){
            rule.validate(agent, bsoDocument, bsoStatus, checkDate);
        }
        
        return ValidationResult.ok();
    }
}
