package com.Three2one.elearning.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

public class StudentRegistrationForm {

	@NotEmpty
	@NotNull
	@Size(min = 6, max = 100, message = "Email must be between 6 and 100 characters")
	@Email(message = "Invalid Email Format !")
	private String studentEmail;
	@NotEmpty
	@NotNull
	@Size(min = 6, max = 100, message = "Email must be between 6 and 100 characters")
	private String courseCode;

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

}
