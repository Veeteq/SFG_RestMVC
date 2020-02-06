package com.wojnarowicz.sfg.gw.validators;

import java.time.LocalDateTime;
import java.util.Optional;

import com.wojnarowicz.sfg.gw.domain.Agent;
import com.wojnarowicz.sfg.gw.domain.BsoDocument;
import com.wojnarowicz.sfg.gw.domain.BsoStatus;

public class ValidationForBso implements ValidationRule {

	@Override
	public void validate(Optional<Agent> agent, Optional<BsoDocument> bsoDocument, BsoStatus bsoStatus, LocalDateTime issueDate) {
		if(!bsoDocument.isPresent()) {
			throw new IllegalArgumentException("БСО не найден в учетных системах. Результат поиска по ЕКИС: Бланк не найден в ЕКИС Результат поиска по АРМ4: БСО не найден");
		}
	}
}
