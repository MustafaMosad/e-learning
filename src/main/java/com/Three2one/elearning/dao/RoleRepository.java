package com.Three2one.elearning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Three2one.elearning.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
