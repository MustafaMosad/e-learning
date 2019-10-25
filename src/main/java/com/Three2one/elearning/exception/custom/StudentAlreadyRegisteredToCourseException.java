package com.Three2one.elearning.exception.custom;

public class StudentAlreadyRegisteredToCourseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public StudentAlreadyRegisteredToCourseException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentAlreadyRegisteredToCourseException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}