package com.Three2one.elearning.mapper;

import com.Three2one.elearning.dto.CourseForm;
import com.Three2one.elearning.model.Course;

public class CourseMapper {

	/**
	 * 
	 * @param courseForm
	 * @return
	 */
	public static Course getDao(CourseForm courseForm) {

		if (courseForm == null)
			return null;

		Course course = new Course();

		course.setDescription(courseForm.getDescription());
		course.setInstructor(courseForm.getInstructor());
		course.setName(courseForm.getName());
		course.setPublishDate(courseForm.getPublishDate());
		course.setTottalHours(courseForm.getTottalHours());
		course.setCourseCode(courseForm.getCourseCode());

		return course;
	}

	/**
	 * 
	 * @param course
	 * @return
	 */
	public static CourseForm getDto(Course course) {

		if (course == null)
			return null;

		CourseForm courseForm = new CourseForm();

		courseForm.setDescription(course.getDescription());
		courseForm.setInstructor(course.getInstructor());
		courseForm.setName(course.getName());
		courseForm.setPublishDate(course.getPublishDate());
		courseForm.setTottalHours(course.getTottalHours());
		courseForm.setCourseCode(course.getCourseCode());

		return courseForm;
	}
}
