package com.faminto.votacaoalmocoapi.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.faminto.votacaoalmocoapi.message.MessageHandler;

@ControllerAdvice
@RestController
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

	private final MessageHandler messageHandler;

	@Autowired
	public BaseExceptionHandler(MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
		String message = messageHandler.getMessage(ex.getMessageKey(), ex.getArgs());
		return new ResponseEntity<>(new ErrorDetails(message), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BusinessException.class)
	public final ResponseEntity<Object> handleBusinessException(BusinessException ex) {
		String message = messageHandler.getMessage(ex.getMessageKey(), ex.getArgs());
		return new ResponseEntity<>(new ErrorDetails(message), HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
		return new ResponseEntity<>(new ErrorDetails(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
