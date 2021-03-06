package com.Three2one.elearning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Three2one.elearning.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	public Course findByCourseCode(String courseCode);

	public Course findByName(String courseName);
}
