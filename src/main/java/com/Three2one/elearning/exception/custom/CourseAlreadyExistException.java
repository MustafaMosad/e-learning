package com.Three2one.elearning.exception.custom;

public class CourseAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public CourseAlreadyExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseAlreadyExistException(String errorMessage) {
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
