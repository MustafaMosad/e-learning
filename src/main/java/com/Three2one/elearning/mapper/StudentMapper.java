package com.Three2one.elearning.mapper;

import com.Three2one.elearning.dto.StudentForm;
import com.Three2one.elearning.model.Student;

public class StudentMapper {

	public static Student getDao(StudentForm studentForm) {

		if (studentForm == null)
			return null;

		Student student = new Student();

		student.setEmail(studentForm.getEmail());
		student.setName(studentForm.getName());
		student.setDateOfBirth(studentForm.getDateOfBirth());
		student.setPassword(studentForm.getPassword());
		student.setUsername(studentForm.getUsername());

		return student;
	}

	public static StudentForm getDto(Student student) {

		if (student == null)
			return null;

		StudentForm studentForm = new StudentForm();

		studentForm.setEmail(student.getEmail());
		studentForm.setName(student.getName());
		studentForm.setDateOfBirth(student.getDateOfBirth());
		studentForm.setPassword(student.getPassword());
		studentForm.setUsername(student.getUsername());

		return studentForm;
	}
}
