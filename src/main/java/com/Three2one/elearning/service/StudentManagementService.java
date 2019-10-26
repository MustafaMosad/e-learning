package com.Three2one.elearning.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Three2one.elearning.dao.RoleRepository;
import com.Three2one.elearning.dao.StudentRepository;
import com.Three2one.elearning.dto.StudentForm;
import com.Three2one.elearning.exception.custom.StudentAlreadyExistException;
import com.Three2one.elearning.mapper.StudentMapper;
import com.Three2one.elearning.model.Role;
import com.Three2one.elearning.model.Student;

@Service
public class StudentManagementService {

	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

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

		Student student = StudentMapper.getDao(studentForm);
		// bcrypt user password
		student.setPassword(encodePassword(studentForm.getPassword()));

		HashSet<Role> roles = new HashSet<>();

		roles.add(roleRepo.findByName("ROLE_USER"));
		student.setRoles(roles);
		studentRepo.save(student);
	}

	/**
	 * 
	 * @param studentForm
	 * @throws StudentAlreadyExistException
	 */
	public void addAdminUser(StudentForm studentForm) throws StudentAlreadyExistException {

		Student student = StudentMapper.getDao(studentForm);
		// bcrypt user password
		student.setPassword(encodePassword(studentForm.getPassword()));
		HashSet<Role> roles = new HashSet<>();

		roles.add(roleRepo.findByName("ROLE_ADMIN"));
		student.setRoles(roles);

		studentRepo.save(student);
	}

	/**
	 * 
	 * @param password
	 * @return
	 */
	private String encodePassword(String password) {
		return bCryptPasswordEncoder.encode(password);

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
