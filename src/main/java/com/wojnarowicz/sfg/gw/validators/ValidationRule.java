package com.wojnarowicz.sfg.gw.validators;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.wojnarowicz.sfg.gw.domain.Agent;
import com.wojnarowicz.sfg.gw.domain.BsoDocument;
import com.wojnarowicz.sfg.gw.domain.BsoStatus;

@FunctionalInterface
public interface ValidationRule {
    void validate(Optional<Agent> agent, List<BsoDocument> bsoList, BsoStatus bsoStatus, LocalDateTime issueDate);
}
