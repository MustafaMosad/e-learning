package com.Three2one.elearning.controller;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Three2one.elearning.dto.StudentForm;
import com.Three2one.elearning.exception.custom.AuthenticationException;
import com.Three2one.elearning.exception.custom.StudentAlreadyExistException;
import com.Three2one.elearning.security.dto.req.JwtTokenRequest;
import com.Three2one.elearning.security.dto.res.JwtTokenResponse;
import com.Three2one.elearning.security.util.JwtTokenUtil;
import com.Three2one.elearning.service.StudentManagementService;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private StudentManagementService studentManagementService;

	@Value("${jwt.http.request.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody @Valid StudentForm studentForm) throws StudentAlreadyExistException {

		studentManagementService.addStudent(studentForm);
		return ResponseEntity.ok(studentForm);
	}

	@RequestMapping(value = "${jwt.get.token.uri}", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest)
			throws AuthenticationException {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtTokenResponse(token));
	}


	private void authenticate(String username, String password) {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new AuthenticationException("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new AuthenticationException("INVALID_CREDENTIALS", e);
		} catch (Exception e) {

			throw new AuthenticationException("INVALID_CREDENTIALS", e);
		}
	}

}
