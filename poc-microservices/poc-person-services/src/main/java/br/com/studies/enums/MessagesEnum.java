package br.com.studies.enums;

public enum MessagesEnum {
	
	USER_NOT_FOUND("User not found");

	private String description;
	
	private MessagesEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
