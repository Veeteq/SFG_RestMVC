package com.wojnarowicz.sfg.gw.validators;

import java.time.LocalDateTime;
import java.util.Optional;

import com.wojnarowicz.sfg.gw.domain.Agent;
import com.wojnarowicz.sfg.gw.domain.BsoDocument;
import com.wojnarowicz.sfg.gw.domain.BsoStatus;

public class ValidationForAgentAndBso implements ValidationRule {

	@Override
	public void validate(Optional<Agent> agent, Optional<BsoDocument> bsoDocument, BsoStatus bsoStatus, LocalDateTime issueDate) {
	    BsoDocument bso = bsoDocument.get();
		if(!bso.getAgent().equals(agent.get())) {
			throw new IllegalArgumentException("Bso not assigned to Agent");
		}
	}
}
