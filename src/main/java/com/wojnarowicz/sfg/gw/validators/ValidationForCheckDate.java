package com.wojnarowicz.sfg.gw.validators;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.wojnarowicz.sfg.gw.domain.Agent;
import com.wojnarowicz.sfg.gw.domain.BsoDocument;
import com.wojnarowicz.sfg.gw.domain.BsoStatus;

public class ValidationForCheckDate implements ValidationRule {

	@Override
	public void validate(Optional<Agent> agent, List<BsoDocument> bsoList, BsoStatus bsoStatus, LocalDateTime checkDate) {
	    BsoDocument bso = bsoList.get(0);
		if(bso.getUpdateDate().toLocalDate().isAfter(checkDate.toLocalDate())) {
			throw new IllegalArgumentException("Дата выдачи БСО не может быть ранее даты смены статуса БСО");
		}
	}
}
