package com.Three2one.elearning.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.Three2one.elearning.exception.custom.AuthenticationException;
import com.Three2one.elearning.exception.custom.CourseAlreadyExistException;
import com.Three2one.elearning.exception.custom.CourseNotFoundException;
import com.Three2one.elearning.exception.custom.StudentAlreadyExistException;
import com.Three2one.elearning.exception.custom.StudentAlreadyRegisteredToCourseException;
import com.Three2one.elearning.exception.custom.StudentNotFoundException;
import com.Three2one.elearning.exception.model.ExceptionResponse;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * handle syntax errors of rest calls
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		logger.info("Start of handleMethodArgumentNotValid");
		List<String> messages = new ArrayList<String>();

		messages.add("Syntax Error");
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.name(), messages);
		logger.info("End of handleHttpMessageNotReadable");

		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * handle javax validations exception and build error response object with
	 * validation messages getting all error messages and add it to Exceptions
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		logger.info("Start of handleMethodArgumentNotValid");
		List<String> messages = new ArrayList<String>();

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			messages.add(error.getDefaultMessage());
		}
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.name(), messages);
		logger.info("End of handleMethodArgumentNotValid");

		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CourseAlreadyExistException.class)
	public ResponseEntity<ExceptionResponse> handleTCourseAlreadyExist(CourseAlreadyExistException ex) {

		logger.info("Start of handleTCourseAlreadyExist");
		List<String> messages = new ArrayList<String>();
		messages.add(ex.getErrorMessage());

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), HttpStatus.CONFLICT.value(),
				HttpStatus.CONFLICT.name(), messages);
		logger.info("End of handleTCourseAlreadyExist");

		return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);

	}

	@ExceptionHandler(StudentAlreadyRegisteredToCourseException.class)
	public ResponseEntity<ExceptionResponse> handleTStudentAlreadyRegisteredToCourse(
			StudentAlreadyRegisteredToCourseException ex) {

		logger.info("Start of handleTStudentAlreadyRegisteredToCourse");
		List<String> messages = new ArrayList<String>();
		messages.add(ex.getErrorMessage());

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), HttpStatus.CONFLICT.value(),
				HttpStatus.CONFLICT.name(), messages);
		logger.info("End of handleTStudentAlreadyRegisteredToCourse");

		return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);

	}

	@ExceptionHandler(StudentAlreadyExistException.class)
	public ResponseEntity<ExceptionResponse> handleTStudentAlreadyExist(StudentAlreadyExistException ex) {

		logger.info("Start of handleTCStudentAlreadyExist");
		List<String> messages = new ArrayList<String>();
		messages.add(ex.getErrorMessage());

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), HttpStatus.CONFLICT.value(),
				HttpStatus.CONFLICT.name(), messages);
		logger.info("End of handleTStudentAlreadyExist");

		return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);

	}

	@ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleCourseNotFound(CourseNotFoundException ex) {

		logger.info("Start of handleCourseNotFound");
		List<String> messages = new ArrayList<String>();
		messages.add(ex.getErrorMessage());

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.name(), messages);
		logger.info("End of handleCourseNotFound");

		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleStudentNotFound(StudentNotFoundException ex) {

		logger.info("Start of handleStudentNotFound");
		List<String> messages = new ArrayList<String>();
		messages.add(ex.getErrorMessage());

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.name(), messages);
		logger.info("End of handleStudentNotFound");

		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ExceptionResponse> handleAuthenticationError(AuthenticationException ex) {

		logger.info("Start of handleAuthenticationError");
		List<String> messages = new ArrayList<String>();
		messages.add(ex.getMessage());

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), HttpStatus.UNAUTHORIZED.value(),
				HttpStatus.UNAUTHORIZED.name(), messages);
		logger.info("End of handleAuthenticationError");

		return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);

	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ExceptionResponse> handleAccessDenied(AccessDeniedException ex) {

		logger.info("Start of handleAccessDenied");
		List<String> messages = new ArrayList<String>();
		messages.add("You are not authorized to access this resource");

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), HttpStatus.FORBIDDEN.value(),
				HttpStatus.FORBIDDEN.name(), messages);
		logger.info("End of handleAccessDenied");

		return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleUnknownException(Exception ex, WebRequest request) {

		logger.info("Start of handleUnknownException");
		List<String> messages = new ArrayList<String>();
		messages.add("Unexpected Error !");
		logger.debug(ex.getMessage());
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), messages);
		logger.info("End of handleUnknownException");

		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}