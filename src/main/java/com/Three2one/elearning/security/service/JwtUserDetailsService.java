package com.Three2one.elearning.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Three2one.elearning.dao.StudentRepository;
import com.Three2one.elearning.model.Student;
import com.Three2one.elearning.security.model.JwtUserDetails;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private StudentRepository studentRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Student student = studentRepo.findByEmail(email);

		if (student == null) {

			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", email));

		}

		return new JwtUserDetails(student.getId(), student.getEmail(), student.getPassword(),
				student.getRoles().iterator().next().getName());
	}

}
