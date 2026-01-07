package system.inventory.commonutilities.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<String> mainException(RuntimeException e){
		return new ResponseEntity<>("An error occurred:"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	public ResponseEntity<String> resourceNotFound(ResourceNotFoundException ref){
		return new ResponseEntity<>("An error occured :"+ref.getMessage(),HttpStatus.NOT_FOUND);
		
	}

}
