package ec.com.corebank.banquito.ErrorManagment;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import ec.com.corebank.banquito.services.ServicesImp.ClienteServiceImp;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorDetails> resourceNotFoundException(CustomException ex, WebRequest request) {
		logger.error(ex.getMessage(), ex);
		ErrorDetails message = new ErrorDetails(
			ex.getHttpStatus().value(),
			new Date(),
			ex.getMessage(),
			request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(message, ex.getHttpStatus());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> globalExceptionHandler(Exception ex, WebRequest request) {
		logger.error(ex.getMessage(), ex);
		ErrorDetails message = new ErrorDetails(
			HttpStatus.INTERNAL_SERVER_ERROR.value(),
			new Date(),
			ex.getMessage(),
			request.getDescription(false));
			
			return new ResponseEntity<ErrorDetails>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
