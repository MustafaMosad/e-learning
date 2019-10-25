package com.Three2one.elearning.dto;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

public class CourseForm {

	@NotEmpty
	@NonNull
	@Size(min = 2, max = 100, message = "Name must be between 10 and 200 characters")
	private String name;
	@Size(min = 10, max = 200, message = "Description must be between 10 and 200 characters")
	private String description;
	@NotEmpty
	@NonNull
	@Size(min = 2, max = 100, message = "Instructor must be between 2 and 100 characters")
	private String instructor;
	@NotEmpty
	@NonNull
	private Date publishDate;
	@NotEmpty
	@NonNull
	@Min(value = 1, message = "Tottal Hours should not be less than 1")
	@Max(value = 400, message = "Tottal Hours should not be greater than 400")
	private Integer tottalHours;
	private String courseCode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Integer getTottalHours() {
		return tottalHours;
	}

	public void setTottalHours(Integer tottalHours) {
		this.tottalHours = tottalHours;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

}
