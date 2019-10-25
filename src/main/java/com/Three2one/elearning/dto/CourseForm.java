package com.Three2one.elearning.dto;

import java.util.Date;

public class CourseForm {

	private String name;
	private String description;
	private String instructor;
	private Date publishDate;
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
