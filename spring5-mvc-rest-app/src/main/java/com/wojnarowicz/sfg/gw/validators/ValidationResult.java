package com.wojnarowicz.sfg.gw.validators;

public class ValidationResult {

	private boolean valid;
	private String messsage;

	private ValidationResult(boolean valid, String messsage) {
		this.valid = valid;
		this.messsage = messsage;
	}

	public boolean isValid() {
		return valid;
	}

	public String getMesssage() {
		return messsage;
	}

	public static ValidationResult ok() {
		return new ValidationResult(true, "OK");

	}

	public static ValidationResult fail(String message) {
		return new ValidationResult(false, message);
	}
	
	public void throwIfInvalid() {
		if(!isValid()) throw new IllegalArgumentException(messsage);
	}
}
