package br.com.pismo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(RequisicaoInvalidaException.class)
    public final ResponseEntity<?> RequisicaoInvalidaException(RequisicaoInvalidaException ex) {
        return new ResponseEntity<ErrorDetails>(getError(ex, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(DatabaseException.class)
    public final ResponseEntity<?> DatabaseException(DatabaseException ex) {
        return new ResponseEntity<ErrorDetails>(getError(ex, HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

	private ErrorDetails getError(ApplicationException ex, HttpStatus status) {
		ErrorDetails errorDetail = new ErrorDetails();
		errorDetail.setStatus(status.value());
		errorDetail.setDetail(ex.getMessage());
		errorDetail.setDeveloperMessage(ex.getClass().getName());

		return errorDetail;
	}
}
