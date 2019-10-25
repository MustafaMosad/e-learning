package com.Three2one.elearning.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Three2one.elearning.dao.CourseRepository;
import com.Three2one.elearning.dto.CourseForm;
import com.Three2one.elearning.exception.custom.CourseAlreadyExistException;
import com.Three2one.elearning.mapper.CourseMapper;
import com.Three2one.elearning.model.Course;

@Service
public class CourseManagementService {

	@Autowired
	private CourseRepository courseRepo;

	/**
	 * get All available Courses
	 * 
	 * @return
	 */
	public List<CourseForm> getAllAvailableCourses() {

		List<Course> courses = courseRepo.findAll();
		List<CourseForm> coursesDtoList = new ArrayList<CourseForm>();

		for (Course course : courses) {
			coursesDtoList.add(CourseMapper.getDto(course));
		}
		return coursesDtoList;
	}

	/**
	 * 
	 * @param courseForm
	 * @return
	 * @throws CourseAlreadyExistException
	 */
	public CourseForm addCourse(CourseForm courseForm) throws CourseAlreadyExistException {

		if (checkIfCourseNameExist(courseForm.getName()))
			throw new CourseAlreadyExistException(courseForm.getName() + " Course Already Exist");

		Course course = CourseMapper.getDao(courseForm);
		String courseCode = generateCourseCode();

		course.setCourseCode(courseCode);
		courseRepo.save(course);

		courseForm.setCourseCode(courseCode);
		return courseForm;
	}

	/**
	 * 
	 * @param courseName
	 * @return
	 */
	private boolean checkIfCourseNameExist(String courseName) {

		Course course = courseRepo.findByName(courseName);

		return course == null ? false : true;
	}

	/**
	 * 
	 * @param courseCode
	 */
	public void removeCourseByCode(String courseCode) {
		Course course = courseRepo.findByCourseCode(courseCode);
		courseRepo.delete(course);
	}

	public CourseRepository getCourseRepo() {
		return courseRepo;
	}

	public void setCourseRepo(CourseRepository courseRepo) {
		this.courseRepo = courseRepo;
	}

	/**
	 * This is Helper method for just generating java UUId
	 * 
	 * @return
	 */
	private String generateCourseCode() {

		return java.util.UUID.randomUUID().toString();
	}
}
