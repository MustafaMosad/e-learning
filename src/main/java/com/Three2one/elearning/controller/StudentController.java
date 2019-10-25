package com.Three2one.elearning.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Three2one.elearning.dto.StudentForm;
import com.Three2one.elearning.service.StudentManagementService;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private StudentManagementService studentManagementService;

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody @Valid StudentForm studentForm) {

		studentManagementService.addStudent(studentForm);
		return ResponseEntity.ok(studentForm);
	}

}
