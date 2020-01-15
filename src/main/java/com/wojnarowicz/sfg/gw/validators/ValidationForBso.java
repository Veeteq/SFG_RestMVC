package com.wojnarowicz.sfg.gw.validators;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.wojnarowicz.sfg.gw.domain.Agent;
import com.wojnarowicz.sfg.gw.domain.BsoDocument;
import com.wojnarowicz.sfg.gw.domain.BsoStatus;

public class ValidationForBso implements ValidationRule {

	@Override
	public void validate(Optional<Agent> agent, List<BsoDocument> bsoList, BsoStatus bsoStatus, LocalDateTime issueDate) {
		if(bsoList.size() == 0) {
			throw new IllegalArgumentException("БСО не найден в учетных системах. Результат поиска по ЕКИС: Бланк не найден в ЕКИС Результат поиска по АРМ4: БСО не найден");
		}
		if(bsoList.size() > 1) {
			throw new IllegalArgumentException("Non-unique Bso found");
		}
	}
}
