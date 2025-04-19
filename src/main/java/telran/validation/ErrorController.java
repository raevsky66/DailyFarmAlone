package telran.validation;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import lombok.AllArgsConstructor;

/**
 * Example of user-defined error data
 */
@AllArgsConstructor
class MyErrorData {
	public Object errorMessage;
	public LocalDateTime occurredOn;
	public String status;
	// …
}

/**
 * The @ControllerAdvice annotation inserts the annotated class in the chain of Response Handling from all controllers.
 * (When inherited from abstract ResponseEntityExceptionHandler, user-defined handler would override protected methods
 * already implemented in the base class.)
 */
@ControllerAdvice() // where to look for controllers
public class ErrorController {  //extends ResponseEntityExceptionHandler {

	//@Override
    //public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	
	/**
	 * This example formats the specific data provided by  MethodArgumentNotValidException
	 */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> myHandler(MethodArgumentNotValidException ex, WebRequest request) {
    	
        Map<String, String> errors = new HashMap<>();
        ex.getAllErrors().forEach((error) -> {
            String fieldName = (error instanceof FieldError) ? ((FieldError) error).getField(): "<common>";
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(HandlerMethodValidationException.class)
	ResponseEntity<Object> methodArgumentNotValidHandler(HandlerMethodValidationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getAllErrors().forEach((error) -> {
            String fieldName = error.getCodes()[0];
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { Exception.class }) // default handler for the rest of Exceptions
	public ResponseEntity<Object> myHandler3(Exception ex, WebRequest request) {
		String errMsg = HttpStatus.BAD_REQUEST.getReasonPhrase();
		MyErrorData errorResponse = new MyErrorData(ex.getMessage(), LocalDateTime.now(), errMsg);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	
}
