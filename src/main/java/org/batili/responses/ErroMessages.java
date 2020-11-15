package org.batili.responses;

public enum ErroMessages {
	MISSING_REQUIRED_FIELD("Missing required field."),
    RECORD_ALREADY_EXISTS("Record already exists."),
    INTERNAL_SERVER_ERROR("Internal  app server error."),
    NO_RECORD_FOUND("Record with provided id is not found.");
	
	 private String errorMessage;

	private ErroMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	 

}
