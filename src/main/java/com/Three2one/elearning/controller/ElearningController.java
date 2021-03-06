package com.Three2one.elearning.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Three2one.elearning.dto.StudentForm;
import com.Three2one.elearning.dto.StudentRegistrationForm;
import com.Three2one.elearning.exception.custom.CourseNotFoundException;
import com.Three2one.elearning.exception.custom.StudentAlreadyRegisteredToCourseException;
import com.Three2one.elearning.exception.custom.StudentNotFoundException;
import com.Three2one.elearning.model.Course;
import com.Three2one.elearning.service.ELearningManagementService;

@RestController
@RequestMapping("/api/e-learning")
public class ElearningController {

	@Autowired
	private ELearningManagementService eLearningManagementService;

	@PostMapping("/register-student-on-course")
	public ResponseEntity<?> registerStudentOnCourse(
			@RequestBody @Valid StudentRegistrationForm studentRegistrationForm)
			throws StudentNotFoundException, CourseNotFoundException, StudentAlreadyRegisteredToCourseException {
		eLearningManagementService.registerStudentOnCourse(studentRegistrationForm);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/unregister-student-on-course")
	public ResponseEntity<?> unregisterStudentOnCourse(
			@RequestBody @Valid StudentRegistrationForm studentRegistrationForm)
			throws StudentNotFoundException, CourseNotFoundException {
		eLearningManagementService.unregisterStudentOnCourse(studentRegistrationForm);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/student-courses")
	public ResponseEntity<?> getStudentCourse(@RequestBody StudentForm studentForm) throws StudentNotFoundException {
		List<Course> studentCourses = eLearningManagementService.getStudentCourses(studentForm.getEmail());
		return ResponseEntity.ok(studentCourses);

	}
}
