package com.wojnarowicz.sfg.gw.validators;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.wojnarowicz.sfg.gw.domain.Agent;
import com.wojnarowicz.sfg.gw.domain.BsoDocument;
import com.wojnarowicz.sfg.gw.domain.BsoStatus;

public class ValidationForAgent implements ValidationRule{

	@Override
	public void validate(Optional<Agent> agent, List<BsoDocument> bsoList, BsoStatus bsoStatus, LocalDateTime issueDate) {
		if(!agent.isPresent()) {
			throw new IllegalArgumentException("Agent not found");
		}
	}
}
