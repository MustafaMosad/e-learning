package com.Three2one.elearning.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Three2one.elearning.dto.CourseForm;
import com.Three2one.elearning.service.CourseManagementService;

@RestController
@RequestMapping("/api/course")
public class CourseController {

	@Autowired
	private CourseManagementService courseManagementService;

	@PostMapping()
	public ResponseEntity<?> addCourse(@RequestBody @Valid CourseForm courseForm) {
		CourseForm courseFormDto = courseManagementService.addCourse(courseForm);
		return ResponseEntity.ok(courseFormDto);
	}

	@GetMapping
	public ResponseEntity<?> getAllCourses() {

		List<CourseForm> courses = courseManagementService.getAllAvailableCourses();
		return ResponseEntity.ok(courses);
	}

}
