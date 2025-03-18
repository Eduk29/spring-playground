package br.com.studies.enums;

public enum MessagesEnum {
	
	CPF_MUST_BE_UNIQUE("This CPF is already registered!"),
	FILTER_MUST_BE_PROVIDED("The $filter must be passed!"),
	INVALID_FILTER_PARAMETERS("The $filter providede is invalid!"),
	USER_NOT_FOUND("User not found!");

	private String description;
	
	private MessagesEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
