package demo.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleUserNotFound(UserNotFoundException e) {
		Map<String, Object> map = new HashMap<>();
		map.put("message", e.getMessage());
		map.put("status", HttpStatus.NOT_FOUND.value());
		map.put("timestamp", LocalDateTime.now());

		return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
	}

	// For validation errors
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {

		Map<String, Object> error = new HashMap<>();
		Map<String, String> validationErrors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(err -> validationErrors.put(err.getField(), err.getDefaultMessage()));

		error.put("message", "Validation failed");
		error.put("errors", validationErrors);
		error.put("status", HttpStatus.BAD_REQUEST.value());
		error.put("timestamp", LocalDateTime.now());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {

		Map<String, Object> error = new HashMap<>();
		error.put("message", "Something went wrong");
		error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.put("timestamp", LocalDateTime.now());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
