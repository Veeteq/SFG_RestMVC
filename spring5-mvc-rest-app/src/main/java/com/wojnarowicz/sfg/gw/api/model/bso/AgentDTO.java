package com.wojnarowicz.sfg.gw.api.model.bso;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value = Include.NON_EMPTY)
public class AgentDTO {

	private Long code;
	private Long lnr;
	private Long skk;
	private String firstName;
	private String middleName;
	private String lastName;
}
