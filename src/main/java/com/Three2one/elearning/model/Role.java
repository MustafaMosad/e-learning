package com.Three2one.elearning.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {
	private Long id;
	private String name;
	private Set<Student> students;

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(mappedBy = "roles")
	public Set<Student> getStudents() {
		return students;
	}

	public void setUsers(Set<Student> students) {
		this.students = students;
	}

}
