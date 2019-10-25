package com.Three2one.elearning.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

public class StudentForm {

	@NotEmpty
	@NotNull
	@Size(min = 2, max = 100, message = "Name must be between 10 and 200 characters")
	private String name;
	@NotEmpty
	@NotNull
	@Size(min = 2, max = 100, message = "Username must be between 10 and 200 characters")
	private String username;
	@NotEmpty
	@NotNull
	@Size(min = 6, max = 100, message = "Email must be between 6 and 100 characters")
	@Email(message = "Invalid Email Format !")
	private String email;
	@NotEmpty
	@NotNull
	@Size(min = 6, max = 32, message = "Password must be between 6 and 32 characters")
	private String password;

	private Date dateOfBirth;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
