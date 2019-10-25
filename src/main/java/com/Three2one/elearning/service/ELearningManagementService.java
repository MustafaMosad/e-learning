package com.Three2one.elearning.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Three2one.elearning.dao.CourseRepository;
import com.Three2one.elearning.dao.StudentRepository;
import com.Three2one.elearning.dto.StudentRegistrationForm;
import com.Three2one.elearning.exception.custom.CourseNotFoundException;
import com.Three2one.elearning.exception.custom.StudentAlreadyRegisteredToCourseException;
import com.Three2one.elearning.exception.custom.StudentNotFoundException;
import com.Three2one.elearning.model.Course;
import com.Three2one.elearning.model.Student;

@Service
public class ELearningManagementService {

	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private CourseRepository courseRepo;

	/**
	 * 
	 * @param studentEmail
	 * @return
	 * @throws StudentNotFoundException
	 */
	public List<Course> getStudentCourses(String studentEmail) throws StudentNotFoundException {

		Student student = studentRepo.findByEmail(studentEmail);

		if (student == null)
			throw new StudentNotFoundException("student with email " + studentEmail + " not exist");

		return new ArrayList<>(student.getCourses());
	}

	/**
	 * 
	 * @param studentRegistrationForm
	 * @throws StudentNotFoundException
	 * @throws CourseNotFoundException
	 * @throws StudentAlreadyRegisteredToCourseException
	 */
	public void registerStudentOnCourse(StudentRegistrationForm studentRegistrationForm)
			throws StudentNotFoundException, CourseNotFoundException, StudentAlreadyRegisteredToCourseException {

		// find student courses
		Student student = studentRepo.findByEmail(studentRegistrationForm.getStudentEmail());
		if (student == null)
			throw new StudentNotFoundException(
					"student with email " + studentRegistrationForm.getStudentEmail() + " not exist");

		Set<Course> studentCourses = student.getCourses();
		// add this course to student
		Course course = courseRepo.findByCourseCode(studentRegistrationForm.getCourseCode());

		if (course == null)
			throw new CourseNotFoundException(
					"course with code " + studentRegistrationForm.getCourseCode() + "not exist");

		if (studentCourses.contains(course)) {
			throw new StudentAlreadyRegisteredToCourseException(" student already Registered to this Course");
		}
		studentCourses.add(course);
		// update student courses
		student.setCourses(studentCourses);
		studentRepo.save(student);

	}

	/**
	 * 
	 * @param studentRegistrationForm
	 * @throws StudentNotFoundException
	 * @throws CourseNotFoundException
	 */
	public void unregisterStudentOnCourse(StudentRegistrationForm studentRegistrationForm)
			throws StudentNotFoundException, CourseNotFoundException {

		// find student courses
		Student student = studentRepo.findByEmail(studentRegistrationForm.getStudentEmail());
		if (student == null)
			throw new StudentNotFoundException(
					"student with email " + studentRegistrationForm.getStudentEmail() + " not exist");
		Set<Course> studentCourses = student.getCourses();
		// remove this course from student
		Course course = courseRepo.findByCourseCode(studentRegistrationForm.getCourseCode());
		if (course == null)
			throw new CourseNotFoundException(
					"course with code " + studentRegistrationForm.getCourseCode() + "not exist");
		studentCourses.remove(course);
		// update student courses
		student.setCourses(studentCourses);
		studentRepo.save(student);
	}

	public StudentRepository getStudentRepo() {
		return studentRepo;
	}

	public void setStudentRepo(StudentRepository studentRepo) {
		this.studentRepo = studentRepo;
	}

	public CourseRepository getCourseRepo() {
		return courseRepo;
	}

	public void setCourseRepo(CourseRepository courseRepo) {
		this.courseRepo = courseRepo;
	}

}
