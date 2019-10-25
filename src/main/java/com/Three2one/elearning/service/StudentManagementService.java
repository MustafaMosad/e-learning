package com.Three2one.elearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Three2one.elearning.dao.StudentRepository;
import com.Three2one.elearning.dto.StudentForm;
import com.Three2one.elearning.exception.custom.StudentAlreadyExistException;
import com.Three2one.elearning.mapper.StudentMapper;
import com.Three2one.elearning.model.Student;

@Service
public class StudentManagementService {

	@Autowired
	private StudentRepository studentRepo;

	/**
	 * 
	 * @return
	 */
	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}

	/**
	 * 
	 * @param studentForm
	 * @throws StudentAlreadyExistException
	 */
	public void addStudent(StudentForm studentForm) throws StudentAlreadyExistException {

		if (checkIfStudentAlreadyExist(studentForm.getEmail())) {
			throw new StudentAlreadyExistException("student with email " + studentForm.getEmail() + " already exist");
		}
		studentRepo.save(StudentMapper.getDao(studentForm));
	}

	/**
	 * 
	 * @param studentEmail
	 * @return
	 */
	private boolean checkIfStudentAlreadyExist(String studentEmail) {

		Student student = studentRepo.findByEmail(studentEmail);
		return student == null ? false : true;
	}

	/**
	 * 
	 * @param studentEmail
	 */
	public void removeStudent(String studentEmail) {
		Student student = studentRepo.findByEmail(studentEmail);
		studentRepo.delete(student);
	}

	public StudentRepository getStudentRepo() {
		return studentRepo;
	}

	public void setStudentRepo(StudentRepository studentRepo) {
		this.studentRepo = studentRepo;
	}

}
