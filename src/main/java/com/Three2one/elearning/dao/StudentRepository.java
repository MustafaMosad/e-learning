package com.Three2one.elearning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Three2one.elearning.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	Student findByEmail(String email);
}
