package com.Three2one.elearning.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Three2one.elearning.dao.CourseRepository;
import com.Three2one.elearning.dao.StudentRepository;
import com.Three2one.elearning.dto.StudentRegistrationForm;
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
	 */
	public List<Course> getStudentCourses(String studentEmail) {

		Student student = studentRepo.findByEmail(studentEmail);

		return new ArrayList<>(student.getCourses());
	}

	/**
	 * 
	 * @param studentRegistrationForm
	 */
	public void registerStudentOnCourse(StudentRegistrationForm studentRegistrationForm) {

		// find student courses
		Student student = studentRepo.findByEmail(studentRegistrationForm.getStudentEmail());
		Set<Course> studentCourses = student.getCourses();
		// add this course to student
		Course course = courseRepo.findByCourseCode(studentRegistrationForm.getCourseCode());
		studentCourses.add(course);
		// update student courses
		student.setCourses(studentCourses);
		studentRepo.save(student);

	}

	/**
	 * 
	 * @param studentRegistrationForm
	 */
	public void unregisterStudentOnCourse(StudentRegistrationForm studentRegistrationForm) {

		// find student courses
		Student student = studentRepo.findByEmail(studentRegistrationForm.getStudentEmail());
		Set<Course> studentCourses = student.getCourses();
		// remove this course from student
		Course course = courseRepo.findByCourseCode(studentRegistrationForm.getCourseCode());
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
