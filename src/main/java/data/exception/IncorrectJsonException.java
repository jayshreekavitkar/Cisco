package data.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Incorrect Json request. Please check your JSON request")
public class IncorrectJsonException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	public IncorrectJsonException(){
		super(" not available");
	}

}

