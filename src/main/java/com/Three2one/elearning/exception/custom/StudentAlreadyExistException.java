package com.Three2one.elearning.exception.custom;

public class StudentAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public StudentAlreadyExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentAlreadyExistException(String errorMessage) {
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