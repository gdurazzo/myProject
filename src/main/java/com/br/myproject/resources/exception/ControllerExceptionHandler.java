package com.br.myproject.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.br.myproject.services.exception.ObjectAlreadyExistException;
import com.br.myproject.services.exception.ObjectNotFoundException;
import com.ibm.icu.impl.duration.impl.Utils;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e,
			HttpServletRequest request) {

		final HttpStatus status = HttpStatus.NOT_FOUND;
		final StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Object not found",
				e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	
	@ExceptionHandler(ObjectAlreadyExistException.class)
	public ResponseEntity<StandardError> objectAlreadyExistExceptio(ObjectAlreadyExistException e,
			HttpServletRequest request) {

		final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		final StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Object already created",
				e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validationErrorException(MethodArgumentNotValidException e,
			HttpServletRequest request) {

		final HttpStatus status = HttpStatus.BAD_REQUEST;
		final StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Validation Error",
				null, request.getRequestURI());
		e.getBindingResult().getFieldErrors().stream()
				.forEach(x -> err.setMessage(handlingValidationMessage(err.getMessage()) + " " + "Field: "
						+ x.getField() + ", Message: " + "cannot be null" + "."));
		return ResponseEntity.status(status).body(err);
	}
	
	private String handlingValidationMessage(String args) {
		if(null != args){
			return args + " ";
		}
		return "";
	}
}
